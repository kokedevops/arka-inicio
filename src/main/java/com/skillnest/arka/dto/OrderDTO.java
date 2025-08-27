package com.skillnest.arka.dto;

import com.skillnest.arka.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private Double totalAmount;
    private OrderStatus orderStatus;
    private Long userId;
    private List<OrderDetailDTO> orderDetails;
    private Long pymentMethodId;
    private Long shipmentId;
}
