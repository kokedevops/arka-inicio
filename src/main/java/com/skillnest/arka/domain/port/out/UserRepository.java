package com.skillnest.arka.domain.port.out;

import com.skillnest.arka.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long id);
    List<User> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}
