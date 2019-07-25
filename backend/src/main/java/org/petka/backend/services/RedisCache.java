/*
 * Developed by Petka <petar.georgiev@gmail.com>
 * Last modified 7/25/19 3:03 PM.
 * Copyright (c) 2019.
 */

package org.petka.backend.services;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Caching component.
 */
@Slf4j
@Component
public class RedisCache {

    @Cacheable(value = "cache-id", key = "#id", unless = "#result.length() >5")
    public String getPostByID(String id) {
        log.info("Returning value for {}.", id);
        return "id:" + id;
    }
}
