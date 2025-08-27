package com.skillnest.arka.domain.model;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Value
@Builder(toBuilder = true)
public class User {
    Long id;
    String email;
    String password;
    String firstName;
    String lastName;
    String phone;
    String nit;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    Role role;
    Direction direction;
    Set<Order> orders;
    Set<ShopingCart> shopingCarts;
    
    public static User create(String email, String password, String firstName, 
                             String lastName, String phone, String nit, 
                             Role role, Direction direction) {
        LocalDateTime now = LocalDateTime.now();
        return User.builder()
                .email(email)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .phone(phone)
                .nit(nit)
                .role(role)
                .direction(direction)
                .createdAt(now)
                .updatedAt(now)
                .build();
    }
    
    public User update(String email, String firstName, String lastName, 
                      String phone, String nit, Role role, Direction direction) {
        return this.toBuilder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .phone(phone)
                .nit(nit)
                .role(role)
                .direction(direction)
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
