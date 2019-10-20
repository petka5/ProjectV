/*
 * Developed by Petka <petar.georgiev@gmail.com>
 * Last modified 10/20/19 8:42 PM.
 * Copyright (c) 2019.
 */

package org.petka.backend.configurations;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//http://techknowsurfer.com/springboot/getting-started-with-rabbitmq-using-spring-boot/

/**
 * Producer config.
 */
@Configuration
public class RabbitmqRpcProducer {

    @Value("${rabbitmq.rpc.exchange.name}")
    private String rpcExchangeName;

    @Value("${rabbitmq.rpc.queue.name}")
    private String rpcQueueName;

    @Value("${rabbitmq.rpc.routing.key}")
    private String rpcRoutingKey;

    /**
     * Creating an Direct Exchange.
     */
    @Bean(name = "rpcExchangeName")
    public DirectExchange rpcExchange() {
        return new DirectExchange(rpcExchangeName);
    }


    /**
     * Creating a Durable Queue.
     */
    @Bean(name = "rpcQueue")
    public Queue rpcQueue() {
        return QueueBuilder.durable(rpcQueueName).build();

    }

    /**
     * Creating a binding which Binds the Queue to an Exhange using a Routing Key.
     */
    @Bean
    public Binding rpcBinding(@Qualifier("rpcQueue") Queue queue,
                              @Qualifier("rpcExchangeName") DirectExchange rpcExchangeName) {

        return BindingBuilder.bind(queue).to(rpcExchangeName).with(rpcRoutingKey);
    }
}
