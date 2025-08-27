package com.skillnest.arka.infrastructure.adapter.in.web.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class DirectionResponse {
    Long id;
    String address;
    String city;
    String state;
    String zipCode;
    String country;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
