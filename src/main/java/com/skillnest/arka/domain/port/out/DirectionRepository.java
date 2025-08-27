package com.skillnest.arka.domain.port.out;

import com.skillnest.arka.domain.model.Direction;

import java.util.Optional;

public interface DirectionRepository {
    Optional<Direction> findById(Long id);
}
