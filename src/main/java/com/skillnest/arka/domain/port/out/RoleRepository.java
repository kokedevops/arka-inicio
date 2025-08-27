package com.skillnest.arka.domain.port.out;

import com.skillnest.arka.domain.model.Role;

import java.util.Optional;

public interface RoleRepository {
    Optional<Role> findById(Long id);
}
