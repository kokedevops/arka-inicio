package com.skillnest.arka.infrastructure.adapter.in.web.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class RoleResponse {
    Long id;
    String name;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
