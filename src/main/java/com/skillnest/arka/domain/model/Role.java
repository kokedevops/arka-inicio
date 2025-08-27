package com.skillnest.arka.domain.model;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Set;

@Value
@Builder(toBuilder = true)
public class Role {
    Long id;
    String name;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    Set<Permit> permits;
    
    public static Role create(String name, Set<Permit> permits) {
        LocalDateTime now = LocalDateTime.now();
        return Role.builder()
                .name(name)
                .permits(permits)
                .createdAt(now)
                .updatedAt(now)
                .build();
    }
    
    public Role update(String name, Set<Permit> permits) {
        return this.toBuilder()
                .name(name)
                .permits(permits)
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
