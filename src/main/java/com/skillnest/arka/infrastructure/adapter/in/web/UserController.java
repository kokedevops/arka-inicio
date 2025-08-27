package com.skillnest.arka.infrastructure.adapter.in.web;

import com.skillnest.arka.domain.model.User;
import com.skillnest.arka.domain.port.in.*;
import com.skillnest.arka.infrastructure.adapter.in.web.dto.CreateUserRequest;
import com.skillnest.arka.infrastructure.adapter.in.web.dto.UpdateUserRequest;
import com.skillnest.arka.infrastructure.adapter.in.web.dto.UserResponse;
import com.skillnest.arka.infrastructure.adapter.in.web.mapper.UserWebMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    
    private final CreateUserUseCase createUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final UserWebMapper userWebMapper;
    
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<User> users = getUserUseCase.getAllUsers();
        List<UserResponse> responses = users.stream()
                .map(userWebMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        Optional<User> user = getUserUseCase.getUserById(id);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        UserResponse response = userWebMapper.toResponse(user.get());
        return ResponseEntity.ok(response);
    }
    
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
        CreateUserCommand command = userWebMapper.toCreateCommand(request);
        User createdUser = createUserUseCase.createUser(command);
        UserResponse response = userWebMapper.toResponse(createdUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, 
                                                  @Valid @RequestBody UpdateUserRequest request) {
        UpdateUserCommand command = userWebMapper.toUpdateCommand(id, request);
        User updatedUser = updateUserUseCase.updateUser(command);
        UserResponse response = userWebMapper.toResponse(updatedUser);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = deleteUserUseCase.deleteUser(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
