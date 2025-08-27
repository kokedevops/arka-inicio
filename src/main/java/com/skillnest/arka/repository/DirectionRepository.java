package com.skillnest.arka.repository;

import com.skillnest.arka.model.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectionRepository  extends JpaRepository<Direction, Long> {
}
