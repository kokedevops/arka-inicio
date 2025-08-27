package com.skillnest.arka.service;

import com.skillnest.arka.dto.RoleDTO;
import com.skillnest.arka.mapper.RoleMapper;
import com.skillnest.arka.model.Permit;
import com.skillnest.arka.model.Role;
import com.skillnest.arka.repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final PermitService permitService;

    public List<RoleDTO> getFindAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roleMapper.roleToRoleDTOs(roles);
    }

    public RoleDTO getRoleById (Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));
        return roleMapper.toRoleDTO(role);
    }

    public Role getRoleEntityById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with id " + id));
    }

    public RoleDTO createRole(RoleDTO role) {
        Role newRole = roleMapper.roleDTOtoRole(role);

        Set<Permit> permits = role.getPermits().stream()
                .map(permitDTO -> permitService.getPermitEntityById(permitDTO.getId()))
                .collect(Collectors.toSet());

        newRole.setPermits(permits);
        Role savedRole = roleRepository.save(newRole);
        return roleMapper.toRoleDTO(savedRole);
    }


    public RoleDTO updateRole (Long id, RoleDTO roleDTO){
        Role existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with id " + id));

        roleMapper.updateRoleFromDTO(roleDTO, existingRole);

        if (roleDTO.getPermits() == null || roleDTO.getPermits().isEmpty()) {
            throw new IllegalArgumentException("El rol debe tener al menos un permiso");
        }

        Set<Permit> permits = roleDTO.getPermits().stream()
                .map(permitDTO -> permitService.getPermitEntityById(permitDTO.getId()))
                .collect(Collectors.toSet());

        existingRole.setPermits(permits);
        Role updatedRole = roleRepository.save(existingRole);
        return roleMapper.toRoleDTO(updatedRole);
    }

    public boolean deleteRole (Long id){
        Optional<Role> roleExisting = roleRepository.findById(id);
        if (roleExisting.isPresent()){
            roleRepository.deleteById(id);
            return  true;
        }
        return  false;
    }

}
