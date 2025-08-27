package com.skillnest.arka.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateUserRequest {
    @Email
    @NotNull(message = "email user cannot be null")
    @NotBlank(message = "the user of user is mandatory")
    String email;
    
    @NotNull(message = "password user cannot be null")
    @NotBlank(message = "the password of user is mandatory")
    String password;
    
    @NotNull(message = "first name user cannot be null")
    @NotBlank(message = "the first name of user is mandatory")
    String firstName;
    
    @NotNull(message = "lastname user cannot be null")
    @NotBlank(message = "the lastname of user is mandatory")
    String lastName;
    
    @NotNull(message = "phone user cannot be null")
    @NotBlank(message = "the phone of user is mandatory")
    String phone;
    
    String nit;
    
    @NotNull(message = "role id cannot be null")
    Long roleId;
    
    @NotNull(message = "direction id cannot be null")
    Long directionId;
}
