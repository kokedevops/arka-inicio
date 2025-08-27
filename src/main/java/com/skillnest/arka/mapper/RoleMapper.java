package com.skillnest.arka.mapper;

import com.skillnest.arka.dto.PermitDTO;
import com.skillnest.arka.dto.RoleDTO;
import com.skillnest.arka.model.Permit;
import com.skillnest.arka.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

//    @Mapping(target = "users", ignore = true)
//    @Mapping(target = "permits", ignore = true)
    RoleDTO toRoleDTO(Role role);

//    @Mapping(target = "users", ignore = true)
//    @Mapping(target = "permits", ignore = true)
    Role roleDTOtoRole(RoleDTO roleDTO);

//    @Mapping(target = "users")
//    @Mapping(target = "permits", ignore = true)
    List<RoleDTO> roleToRoleDTOs(List<Role> roles);

//    @Mapping(target = "users", ignore = true)
//    @Mapping(target = "permits", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updateRoleFromDTO(RoleDTO dto, @MappingTarget Role role);

}
