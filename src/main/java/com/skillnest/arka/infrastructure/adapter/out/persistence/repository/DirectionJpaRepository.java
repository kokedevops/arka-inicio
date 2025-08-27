package com.skillnest.arka.infrastructure.adapter.out.persistence.repository;

import com.skillnest.arka.infrastructure.adapter.out.persistence.entity.DirectionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectionJpaRepository extends JpaRepository<DirectionJpaEntity, Long> {
}
