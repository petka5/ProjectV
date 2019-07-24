/*
 * Developed by Petka <petar.georgiev@gmail.com>
 * Last modified 7/23/19 2:27 PM.
 * Copyright (c) 2019.
 */

package org.petka.backend.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Message domain object, used for rabbitmq messaging..
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private UUID id;
    private String msg;
    private LocalDateTime date;
}
