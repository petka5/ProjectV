/*
 * Developed by Petka <petar.georgiev@gmail.com>
 * Last modified 11/23/19 7:05 PM.
 * Copyright (c) 2019.
 */

package org.petka.backend.controllers;


import org.modelmapper.ModelMapper;
import org.petka.backend.dto.UserDto;
import org.petka.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * User Controller.
 */
@Slf4j
@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Register user endpoint.
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public UserDto register(@RequestBody UserDto user) {
        log.info("Hello world controller.");

        return modelMapper.map(userService.registerUser(user), UserDto.class);
    }
}


