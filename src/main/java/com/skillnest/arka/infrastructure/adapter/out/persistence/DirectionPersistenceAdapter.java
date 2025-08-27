package com.skillnest.arka.infrastructure.adapter.out.persistence;

import com.skillnest.arka.domain.model.Direction;
import com.skillnest.arka.domain.port.out.DirectionRepository;
import com.skillnest.arka.infrastructure.adapter.out.persistence.mapper.DirectionPersistenceMapper;
import com.skillnest.arka.infrastructure.adapter.out.persistence.repository.DirectionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DirectionPersistenceAdapter implements DirectionRepository {
    
    private final DirectionJpaRepository directionJpaRepository;
    private final DirectionPersistenceMapper directionPersistenceMapper;
    
    @Override
    public Optional<Direction> findById(Long id) {
        return directionJpaRepository.findById(id)
                .map(directionPersistenceMapper::toDomain);
    }
}
