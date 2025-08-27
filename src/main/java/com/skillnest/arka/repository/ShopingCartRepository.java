package com.skillnest.arka.repository;

import com.skillnest.arka.model.ShopingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopingCartRepository extends JpaRepository<ShopingCart, Long> {
}
