package com.skillnest.arka.infrastructure.adapter.out.persistence.mapper;

import com.skillnest.arka.domain.model.Role;
import com.skillnest.arka.infrastructure.adapter.out.persistence.entity.RoleJpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PermitPersistenceMapper.class})
public interface RolePersistenceMapper {
    
    Role toDomain(RoleJpaEntity entity);
    RoleJpaEntity toEntity(Role domain);
}
