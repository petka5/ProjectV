package org.petka.backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        log.info("Hello world controller.");
        return "Greetings from Spring Boot!";
    }
}
