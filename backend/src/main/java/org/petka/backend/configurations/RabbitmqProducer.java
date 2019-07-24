/*
 * Developed by Petka <petar.georgiev@gmail.com>
 * Last modified 7/20/19 3:02 PM.
 * Copyright (c) 2019.
 */

package org.petka.backend.configurations;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

//http://techknowsurfer.com/springboot/getting-started-with-rabbitmq-using-spring-boot/

/**
 * Producer config.
 */
@Configuration
public class RabbitmqProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    /**
     * Creating an Direct Exchange.
     */
    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(exchangeName);
    }


    /**
     * Creating a Durable Queue.
     */
    @Bean
    public Queue queue() {
        return QueueBuilder.durable(queueName).build();

    }

    /**
     * Creating a binding which Binds the Queue to an Exhange using a Routing Key.
     */
    @Bean
    public Binding binding(Queue queue, DirectExchange directExchange) {

        return BindingBuilder.bind(queue).to(directExchange).with(routingKey);
    }

    /**
     * Creating a RabbitMq Template using the Connection Factory , which is creating by Springboot auto-configurations.
     */
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory,
                                         Jackson2JsonMessageConverter jackson2JsonMessageConverter) {

        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter);

        return rabbitTemplate;

    }

    /**
     * Setting up a Json Message converter which will convert an Pojo object to JSON message and using the same in
     * RabbitMq Template.
     */
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        return new Jackson2JsonMessageConverter(mapper);
    }
}
