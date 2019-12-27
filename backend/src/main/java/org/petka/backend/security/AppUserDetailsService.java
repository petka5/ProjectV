/*
 * Developed by Petka <petar.georgiev@gmail.com>
 * Last modified 7/28/19 5:49 PM.
 * Copyright (c) 2019.
 */

package org.petka.backend.security;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.petka.backend.persistence.User;
import org.petka.backend.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * AppUserDetailsService.
 */
@Component
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).map(this::getUserDetails)
                .orElseThrow(() ->
                                     new UsernameNotFoundException(
                                             String.format("The username %s doesn't exist", username)));
    }

    private UserDetails getUserDetails(User user) {
        user.setLastLogin(LocalDateTime.now());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                                                                      getUserAuthorities(user));
    }

    private List<GrantedAuthority> getUserAuthorities(User user) {
        return user.getRoles().stream().map(e -> e.getRoleName()).map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
