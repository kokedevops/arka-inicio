package com.skillnest.arka.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.skillnest.arka.model.enums.ShipmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentDTO {
    private Long id;
    private Double shippingCost;
    private String carrier;
    private String trackingNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime shippingDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime estimatedDeliveryDate;
    private ShipmentStatus status;
    private DirectionDTO direction;
}
