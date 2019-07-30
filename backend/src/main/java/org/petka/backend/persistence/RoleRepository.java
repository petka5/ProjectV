/*
 * Developed by Petka <petar.georgiev@gmail.com>
 * Last modified 7/28/19 5:52 PM.
 * Copyright (c) 2019.
 */

package org.petka.backend.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * RoleRepository.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

}
