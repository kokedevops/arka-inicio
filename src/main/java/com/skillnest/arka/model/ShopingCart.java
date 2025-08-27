package com.skillnest.arka.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.skillnest.arka.model.enums.CartStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "shoping_carts")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CartStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
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

    @JsonBackReference(value = "user_cart")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonManagedReference(value = "shoping_cart_detail_cart")
    @OneToMany(mappedBy = "shopingCart", cascade = CascadeType.ALL)
    private List<ShopingCartDetail> shopingCartDetails;
}
