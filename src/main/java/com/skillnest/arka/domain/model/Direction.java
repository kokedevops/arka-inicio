package com.skillnest.arka.domain.model;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder(toBuilder = true)
public class Direction {
    Long id;
    String address;
    String city;
    String state;
    String zipCode;
    String country;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    
    public static Direction create(String address, String city, String state, 
                                 String zipCode, String country) {
        LocalDateTime now = LocalDateTime.now();
        return Direction.builder()
                .address(address)
                .city(city)
                .state(state)
                .zipCode(zipCode)
                .country(country)
                .createdAt(now)
                .updatedAt(now)
                .build();
    }
    
    public Direction update(String address, String city, String state, 
                          String zipCode, String country) {
        return this.toBuilder()
                .address(address)
                .city(city)
                .state(state)
                .zipCode(zipCode)
                .country(country)
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
