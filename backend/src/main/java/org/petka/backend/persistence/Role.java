/*
 * Developed by Petka <petar.georgiev@gmail.com>
 * Last modified 7/28/19 5:53 PM.
 * Copyright (c) 2019.
 */

package org.petka.backend.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Roles.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Role {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "description")
    private String description;
}
