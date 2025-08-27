package com.skillnest.arka.infrastructure.adapter.out.persistence.mapper;

import com.skillnest.arka.domain.model.User;
import com.skillnest.arka.infrastructure.adapter.out.persistence.entity.UserJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RolePersistenceMapper.class, DirectionPersistenceMapper.class})
public interface UserPersistenceMapper {
    
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "shopingCarts", ignore = true)
    User toDomain(UserJpaEntity entity);
    
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "shopingCarts", ignore = true)
    UserJpaEntity toEntity(User domain);
}
