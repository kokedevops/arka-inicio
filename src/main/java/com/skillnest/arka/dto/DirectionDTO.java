package com.skillnest.arka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DirectionDTO {
    private Long id;
    private  String nomenclature;
    private String city;
    private  String country;
}
