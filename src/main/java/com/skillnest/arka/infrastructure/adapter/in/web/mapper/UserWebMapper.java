package com.skillnest.arka.infrastructure.adapter.in.web.mapper;

import com.skillnest.arka.domain.model.User;
import com.skillnest.arka.domain.port.in.CreateUserCommand;
import com.skillnest.arka.domain.port.in.UpdateUserCommand;
import com.skillnest.arka.infrastructure.adapter.in.web.dto.CreateUserRequest;
import com.skillnest.arka.infrastructure.adapter.in.web.dto.UpdateUserRequest;
import com.skillnest.arka.infrastructure.adapter.in.web.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserWebMapper {
    
    UserResponse toResponse(User user);
    
    CreateUserCommand toCreateCommand(CreateUserRequest request);
    
    @Mapping(target = "id", source = "id")
    @Mapping(target = "password", ignore = true)
    UpdateUserCommand toUpdateCommand(Long id, UpdateUserRequest request);
}
