/*
 * Developed by Petka <petar.georgiev@gmail.com>
 * Last modified 11/30/19 11:42 AM.
 * Copyright (c) 2019.
 */

package org.petka.backend.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class.
 */
@Configuration
public class Configurations {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
