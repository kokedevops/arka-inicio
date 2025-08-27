package com.skillnest.arka.domain.model;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder(toBuilder = true)
public class Permit {
    Long id;
    String name;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    
    public static Permit create(String name) {
        LocalDateTime now = LocalDateTime.now();
        return Permit.builder()
                .name(name)
                .createdAt(now)
                .updatedAt(now)
                .build();
    }
    
    public Permit update(String name) {
        return this.toBuilder()
                .name(name)
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
