package com.skillnest.arka.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.skillnest.arka.model.enums.PymentStatus;
import com.skillnest.arka.model.enums.PymentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "payment_methods")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PymentType pymentType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PymentStatus pymentStatus;

    @NotNull(message = "details pyment order user cannot be null")
    @NotBlank(message = "the details pyment order of user is mandatory")
    @Column(nullable = false)
    private String details;

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

    @JsonManagedReference(value = "pyment_order")
    @OneToMany(mappedBy = "pymentMethod")
    private Set<Order> orders;
}
