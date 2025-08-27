package com.skillnest.arka.repository;

import com.skillnest.arka.model.Permit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermitRepository extends JpaRepository<Permit, Long> {
}
