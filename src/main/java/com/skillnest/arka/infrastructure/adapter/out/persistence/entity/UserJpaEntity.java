package com.skillnest.arka.infrastructure.adapter.out.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class UserJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(name = "email", unique = true, nullable = false)
    @NotNull(message = "email user cannot be null")
    @NotBlank(message = "the user of user is mandatory")
    private String email;

    @NotNull(message = "password user cannot be null")
    @NotBlank(message = "the password of user is mandatory")
    @Column(nullable = false)
    private String password;

    @NotNull(message = "first name user cannot be null")
    @NotBlank(message = "the first name of user is mandatory")
    @Column(nullable = false)
    private String firstName;

    @NotNull(message = "lastname user cannot be null")
    @NotBlank(message = "the lastname of user is mandatory")
    @Column(nullable = false)
    private String lastName;

    @NotNull(message = "phone user cannot be null")
    @NotBlank(message = "the phone of user is mandatory")
    @Column(nullable = false)
    private String phone;

    @Column(nullable = true)
    private String nit;

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

    @JsonBackReference(value = "role_user")
    @ManyToOne
    @JoinColumn(name = "rol_id")
    private RoleJpaEntity role;

    @JsonManagedReference(value = "user_direction")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "direction_id")
    private DirectionJpaEntity direction;

    @JsonManagedReference(value = "user_order")
    @OneToMany(mappedBy = "user")
    private Set<OrderJpaEntity> orders;

    @JsonManagedReference(value = "user_cart")
    @OneToMany(mappedBy = "user")
    private Set<ShopingCartJpaEntity> shopingCarts;
}
