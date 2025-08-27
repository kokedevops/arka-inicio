package com.skillnest.arka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopingCartDetailDTO {

    private  Long id;
    private Long cartId;       // Solo el ID de la orden, si es necesario
    private Long productId;
    private String productName;
    private Integer quantity;
    private Double priceUnit;
    private Double totalPrice;
}
