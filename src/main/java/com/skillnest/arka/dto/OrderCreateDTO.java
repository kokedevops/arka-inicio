package com.skillnest.arka.dto;

import lombok.Data;

@Data
public class OrderCreateDTO {
    private Long cartId;
    private Long paymentId;
    private Long shipmentId;
}
