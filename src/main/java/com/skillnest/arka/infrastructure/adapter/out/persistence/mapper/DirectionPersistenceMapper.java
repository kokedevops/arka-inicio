package com.skillnest.arka.infrastructure.adapter.out.persistence.mapper;

import com.skillnest.arka.domain.model.Direction;
import com.skillnest.arka.infrastructure.adapter.out.persistence.entity.DirectionJpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DirectionPersistenceMapper {
    
    Direction toDomain(DirectionJpaEntity entity);
    DirectionJpaEntity toEntity(Direction domain);
}
