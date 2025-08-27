package com.skillnest.arka.repository;

import com.skillnest.arka.model.PymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PymentMethodRepository extends JpaRepository<PymentMethod, Long> {
}
