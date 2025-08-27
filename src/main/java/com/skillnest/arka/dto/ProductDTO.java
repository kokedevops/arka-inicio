package com.skillnest.arka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private Integer stock;
    private float sellingPrice;
    private Integer stockMinimum;
    private String imageUrl;
    private Boolean isActive;
    private CategoryDTO category;
    private BrandDTO brand;
}
