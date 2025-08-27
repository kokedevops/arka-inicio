package com.skillnest.arka.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "shopping_carts")
@AllArgsConstructor
@NoArgsConstructor
public class ShopingCartJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Otros campos simplificados para el ejemplo
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserJpaEntity user;
}
