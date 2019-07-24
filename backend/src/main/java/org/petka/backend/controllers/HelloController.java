package org.petka.backend.controllers;

import java.util.List;

import org.petka.backend.persistence.User;
import org.petka.backend.persistence.UserRepository;
import org.petka.backend.services.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * Test controller - say hello.
 */
@Slf4j
@RestController
public class HelloController {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private MessageProducer messageProducer;

    /**
     * Test request method.
     *
     * @return greeting.
     */
    @RequestMapping("/")
    public String index() {
        log.info("Hello world controller.");
        List<User> all = userRepository.findAll();
        all.forEach(System.out::println);
        messageProducer.produce();
        return "Greetings from Spring Boot!";
    }
}
