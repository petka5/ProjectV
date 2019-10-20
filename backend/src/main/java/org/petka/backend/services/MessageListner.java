/*
 * Developed by Petka <petar.georgiev@gmail.com>
 * Last modified 7/23/19 2:31 PM.
 * Copyright (c) 2019.
 */

package org.petka.backend.services;

import java.util.UUID;

import org.petka.backend.domain.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Message listener.
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class MessageListner {

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void consume(Message message) {
        log.info("Payload {} ", message);
    }

    @RabbitListener(queues = "${rabbitmq.rpc.queue.name}")
    public UUID rpcConsumer(Message message) {
        log.info("RPC call payload {}.", message);
        return message.getId();
    }
}
