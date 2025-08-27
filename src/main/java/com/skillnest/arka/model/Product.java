package com.skillnest.arka.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotNull(message = "name product cannot be null")
    @Column(nullable = false)
    @NotBlank(message = "the name of prduct is mandatory")
    private String name;

    @NotNull(message = "description product cannot be null")
    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "the description of product is mandatory")
    private String description;


    @NotNull(message = "stock product cannot be null")
    @Min(value = 0, message = "Current stock cannot be negative")
    private Integer stock;

    @NotNull(message = "selling_price cannot be null")
    @Column(nullable = false)
    @Min(value = 0, message = "current selling_price cannot be negative")
    private Double sellingPrice;

    @NotNull(message = "stockMinimum cannot be null")
    @Column(nullable = false)
    @Min(value = 0, message = "current stockMinimum cannot be negative")
    private Integer stockMinimum;

    @NotNull(message = "image_url cannot be null")
    @Column(length = 500)
    private String imageUrl;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean isActive = true;

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

    @JsonBackReference(value = "category_product")
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @JsonBackReference(value = "brand_product")
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @JsonManagedReference(value = "order_detail_product")
    @OneToMany(mappedBy = "product")
    private Set<OrderDetail> orderDetails;

    @JsonManagedReference(value = "shoping_cart_detail_product")
    @OneToMany(mappedBy = "product")
    private Set<ShopingCartDetail> shopingCartDetails;
}
