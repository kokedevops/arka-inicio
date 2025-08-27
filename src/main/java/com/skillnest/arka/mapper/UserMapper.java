package com.skillnest.arka.mapper;

import com.skillnest.arka.dto.UserCreateDTO;
import com.skillnest.arka.dto.UserDTO;
import com.skillnest.arka.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Mapea entidad User a DTO general UserDTO
//    @Mapping(source = "role.id", target = "roleId")
//    @Mapping(source = "direction.id", target = "directionId")
    UserDTO userToUserDTO(User user);

    // Mapea DTO de creación UserCreateDTO a entidad User
    //@Mapping(target = "password", ignore = true) // La contraseña se maneja aparte (por ejemplo, en el servicio)
//    @Mapping(source = "roleId", target = "role")
//    @Mapping(source = "directionId", target = "direction")
    User userCreateDTOtoUser(UserCreateDTO dto);

    // Mapea lista de entidades a lista de DTOs
    List<UserDTO> usersToUserDTOs(List<User> users);

    // Actualiza entidad User con datos de UserCreateDTO (sin tocar password)
    //@Mapping(target = "password", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "direction", ignore = true)
    void updateUserFromDTO(UserCreateDTO dto, @MappingTarget User user);
}
