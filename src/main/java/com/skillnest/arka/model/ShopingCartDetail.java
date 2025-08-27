package com.skillnest.arka.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "shoping_cart_detail")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopingCartDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotNull(message = "quantity cannot be null")
    @Column(nullable = false)
    @Min(value = 1, message = "current quantity cannot be negative")
    private Integer quantity;

    @NotNull(message = "price_unit cannot be null")
    @Column(nullable = false)
    @Min(value = 0, message = "current price_unit cannot be negative")
    private Double priceUnit;

    @NotNull(message = "total_price cannot be null")
    @Column(nullable = false)
    @Min(value = 0, message = "current total_price cannot be negative")
    private Double totalPrice;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();

    }

    @JsonBackReference(value = "shoping_cart_detail_cart")
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private ShopingCart shopingCart;

    @JsonBackReference(value = "shoping_cart_detail_product")
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
