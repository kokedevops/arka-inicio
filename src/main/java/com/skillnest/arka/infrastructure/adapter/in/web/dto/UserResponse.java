package com.skillnest.arka.infrastructure.adapter.in.web.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class UserResponse {
    Long id;
    String email;
    String firstName;
    String lastName;
    String phone;
    String nit;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    RoleResponse role;
    DirectionResponse direction;
}
