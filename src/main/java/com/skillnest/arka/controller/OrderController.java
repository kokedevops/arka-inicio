package com.skillnest.arka.controller;

import com.skillnest.arka.dto.OrderCreateDTO;
import com.skillnest.arka.dto.OrderDTO;
import com.skillnest.arka.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping()
    public ResponseEntity<OrderDTO> createOrder(@Valid @RequestBody OrderCreateDTO dto){
        OrderDTO order = orderService.createOrderFromCart(
                dto.getCartId(),
                dto.getPaymentId(),
                dto.getShipmentId()
        );
        return  new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
