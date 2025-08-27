package com.skillnest.arka.infrastructure.adapter.out.persistence.repository;

import com.skillnest.arka.infrastructure.adapter.out.persistence.entity.RoleJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleJpaRepository extends JpaRepository<RoleJpaEntity, Long> {
}
