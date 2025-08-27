package com.skillnest.arka.domain.port.in;

import com.skillnest.arka.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface GetUserUseCase {
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
}
