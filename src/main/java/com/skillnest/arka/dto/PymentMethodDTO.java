package com.skillnest.arka.dto;

import com.skillnest.arka.model.enums.PymentStatus;
import com.skillnest.arka.model.enums.PymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PymentMethodDTO {
    private Long id;
    private PymentType pymentType;
    private PymentStatus pymentStatus;
    private String details;

}
