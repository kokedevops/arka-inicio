package com.skillnest.arka.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "permits")
@NoArgsConstructor
@AllArgsConstructor

public class Permit {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "name permit cannot be null")
    @NotBlank(message = "the name of permit is mandatory")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "description permit cannot be null")
    @NotBlank(message = "the description of permit is mandatory")
    @Column(columnDefinition = "Text", nullable = false)
    private String description;

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

    @JsonBackReference(value = "rol_user")
    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Role role;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "permits_has_roles",
            joinColumns = @JoinColumn(name = "permit_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "permits"})
    private List<Role> roles;
}
