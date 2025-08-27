package com.skillnest.arka.repository;

import com.skillnest.arka.model.ShopingCartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopingCartDetailRepository extends JpaRepository<ShopingCartDetail, Long> {
}
