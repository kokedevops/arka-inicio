package com.skillnest.arka.infrastructure.adapter.out.persistence.mapper;

import com.skillnest.arka.domain.model.Permit;
import com.skillnest.arka.infrastructure.adapter.out.persistence.entity.PermitJpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermitPersistenceMapper {
    
    Permit toDomain(PermitJpaEntity entity);
    PermitJpaEntity toEntity(Permit domain);
}
