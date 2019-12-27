/*
 * Developed by Petka <petar.georgiev@gmail.com>
 * Last modified 7/23/19 2:31 PM.
 * Copyright (c) 2019.
 */

package org.petka.backend.services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.petka.backend.domain.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Message producer.
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Value("${rabbitmq.rpc.exchange.name}")
    private String rpcEchangeName;

    @Value("${rabbitmq.rpc.routing.key}")
    private String rpcRoutingKey;

    private final RabbitTemplate rabbitTemplate;

    /**
     * Produces rabbitmq message.
     */
    public void produce() {

        Message message =
                Message.builder().id(UUID.randomUUID()).msg("Sample Message").date(LocalDateTime.now()).build();

        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
        log.info("Message was sent {}.", message);
    }

    /**
     * Sending RPC call.
     */
    public String rpcProducer() {
        Message message =
                Message.builder().id(UUID.randomUUID()).msg("Sample Message").date(LocalDateTime.now()).build();
        log.info("Sending RPC call {}", message);
        UUID response = (UUID) rabbitTemplate.convertSendAndReceive(rpcEchangeName, rpcRoutingKey, message);
        log.info("Response is {}.", response);
        return "RPC response is: " + response;
    }

}
