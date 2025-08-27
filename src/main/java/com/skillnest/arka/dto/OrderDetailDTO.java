package com.skillnest.arka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    private Long id;
    private Long orderId;       // Solo el ID de la orden, si es necesario
    private Long productId;
    private String productName;
    private Integer quantity;
    private Double priceUnit;
    private Double totalPrice;

}
