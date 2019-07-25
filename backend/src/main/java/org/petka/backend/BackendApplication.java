/*
 * Developed by Petka <petar.georgiev@gmail.com>
 * Last modified 7/20/19 12:22 AM.
 * Copyright (c) 2019.
 */

package org.petka.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Main class of spring boot application.
 */
@SpringBootApplication
@EnableCaching
public class BackendApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
}
