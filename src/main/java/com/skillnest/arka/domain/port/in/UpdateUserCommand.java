package com.skillnest.arka.domain.port.in;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateUserCommand {
    Long id;
    String email;
    String password;
    String firstName;
    String lastName;
    String phone;
    String nit;
    Long roleId;
    Long directionId;
}
