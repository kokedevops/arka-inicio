package com.skillnest.arka.application.service;

import com.skillnest.arka.domain.model.Direction;
import com.skillnest.arka.domain.model.Role;
import com.skillnest.arka.domain.model.User;
import com.skillnest.arka.domain.port.in.*;
import com.skillnest.arka.domain.port.out.DirectionRepository;
import com.skillnest.arka.domain.port.out.RoleRepository;
import com.skillnest.arka.domain.port.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements CreateUserUseCase, GetUserUseCase, UpdateUserUseCase, DeleteUserUseCase {
    
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final DirectionRepository directionRepository;
    
    @Override
    public User createUser(CreateUserCommand command) {
        Role role = roleRepository.findById(command.getRoleId())
                .orElseThrow(() -> new IllegalArgumentException("Role not found with id: " + command.getRoleId()));
                
        Direction direction = directionRepository.findById(command.getDirectionId())
                .orElseThrow(() -> new IllegalArgumentException("Direction not found with id: " + command.getDirectionId()));
        
        User user = User.create(
                command.getEmail(),
                command.getPassword(),
                command.getFirstName(),
                command.getLastName(),
                command.getPhone(),
                command.getNit(),
                role,
                direction
        );
        
        return userRepository.save(user);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    
    @Override
    public User updateUser(UpdateUserCommand command) {
        User existingUser = userRepository.findById(command.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + command.getId()));
                
        Role role = roleRepository.findById(command.getRoleId())
                .orElseThrow(() -> new IllegalArgumentException("Role not found with id: " + command.getRoleId()));
                
        Direction direction = directionRepository.findById(command.getDirectionId())
                .orElseThrow(() -> new IllegalArgumentException("Direction not found with id: " + command.getDirectionId()));
        
        User updatedUser = existingUser.update(
                command.getEmail(),
                command.getFirstName(),
                command.getLastName(),
                command.getPhone(),
                command.getNit(),
                role,
                direction
        );
        
        return userRepository.save(updatedUser);
    }
    
    @Override
    public boolean deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }
}
