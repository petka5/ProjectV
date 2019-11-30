/*
 * Developed by Petka <petar.georgiev@gmail.com>
 * Last modified 11/23/19 7:08 PM.
 * Copyright (c) 2019.
 */

package org.petka.backend.services;

import org.modelmapper.ModelMapper;
import org.petka.backend.dto.UserDto;
import org.petka.backend.persistence.Role;
import org.petka.backend.persistence.RoleRepository;
import org.petka.backend.persistence.User;
import org.petka.backend.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.google.common.collect.ImmutableList;

/**
 * User service.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Register user to the system.
     */
    public User registerUser(UserDto user) {

        return (User) userRepository.findByUsername(user.getUsername())
                .map(e -> {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists.");
                }).orElseGet(() -> saveUser(user));
    }

    private User saveUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(ImmutableList.of(roleRepository.findByRoleName(Role.UserRoles.STANDARD_USER.name())));
        return userRepository.save(user);
    }
}
