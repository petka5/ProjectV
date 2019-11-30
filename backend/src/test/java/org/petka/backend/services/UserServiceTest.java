/*
 * Developed by Petka <petar.georgiev@gmail.com>
 * Last modified 11/30/19 1:15 PM.
 * Copyright (c) 2019.
 */

package org.petka.backend.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.petka.backend.dto.UserDto;
import org.petka.backend.persistence.Role;
import org.petka.backend.persistence.RoleRepository;
import org.petka.backend.persistence.User;
import org.petka.backend.persistence.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;


/**
 * UserService JUnit test.
 */
// https://dzone.com/articles/spring-boot-2-with-junit-5-and-mockito-2-for-unit
@ExtendWith(MockitoExtension.class)
@DisplayName("UserService JUnit test")
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserService userService;

    /**
     * Init the test.
     */
    @BeforeEach
    public void init() {
        ReflectionTestUtils.setField(userService, "modelMapper", new ModelMapper());
        ReflectionTestUtils.setField(userService, "passwordEncoder", new BCryptPasswordEncoder());
        when(userRepository.save(any())).thenAnswer(e -> e.getArguments()[0]);
        when(roleRepository.findByRoleName(any())).thenReturn(Role.builder().roleName("STANDARD_USER").build());

    }

    /**
     * Testing userRegistration.
     */
    @Test
    @DisplayName("Testing userRegistration")
    public void registerUser() {
        User user = userService.registerUser(
                UserDto.builder().username("username").password("password").email("pol@yahoo.com").build());

        Assert.assertNotNull("User must not be null.", user);
    }
}
