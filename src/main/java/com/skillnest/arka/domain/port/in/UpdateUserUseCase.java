package com.skillnest.arka.domain.port.in;

import com.skillnest.arka.domain.model.User;

public interface UpdateUserUseCase {
    User updateUser(UpdateUserCommand command);
}
