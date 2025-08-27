package com.skillnest.arka.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "directions")
@AllArgsConstructor
@NoArgsConstructor
public class Direction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "nomenclature direction cannot be null")
    @NotBlank(message = "the nomenclature of direction is mandatory")
    @Column(nullable = false)
    private String nomenclature;

    @NotNull(message = "city direction cannot be null")
    @NotBlank(message = "the city of direction is mandatory")
    @Column(nullable = false)
    private String city;

    @NotNull(message = "country direction cannot be null")
    @NotBlank(message = "the country of direction is mandatory")
    @Column(nullable = false)
    private String country;

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

    @JsonBackReference(value = "user_direction")
    @OneToOne(mappedBy = "direction", fetch = FetchType.LAZY)
    private User user;

    @JsonManagedReference(value = "shipment_direction")
    @OneToMany(mappedBy = "direction")
    @JsonIgnore
    private Set<Shipment> shipments;


}
