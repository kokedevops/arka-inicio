package com.skillnest.arka.infrastructure.adapter.out.persistence;

import com.skillnest.arka.domain.model.Role;
import com.skillnest.arka.domain.port.out.RoleRepository;
import com.skillnest.arka.infrastructure.adapter.out.persistence.mapper.RolePersistenceMapper;
import com.skillnest.arka.infrastructure.adapter.out.persistence.repository.RoleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RolePersistenceAdapter implements RoleRepository {
    
    private final RoleJpaRepository roleJpaRepository;
    private final RolePersistenceMapper rolePersistenceMapper;
    
    @Override
    public Optional<Role> findById(Long id) {
        return roleJpaRepository.findById(id)
                .map(rolePersistenceMapper::toDomain);
    }
}
