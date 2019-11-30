/*
 * Developed by Petka <petar.georgiev@gmail.com>
 * Last modified 11/30/19 11:41 AM.
 * Copyright (c) 2019.
 */

package org.petka.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private Long id;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String email;
}
