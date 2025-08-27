package com.skillnest.arka.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermitDTO {
    private Long id;

    @NotNull(message = "name permit cannot be null")
    @NotBlank(message = "the name of permit is mandatory")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "description permit cannot be null")
    @NotBlank(message = "the description of permit is mandatory")
    @Column(columnDefinition = "Text", nullable = false)
    private String description;
}
