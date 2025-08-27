package com.skillnest.arka.controller;

import com.skillnest.arka.dto.ShopingCartDTO;
import com.skillnest.arka.service.ShopingCartService;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/shopingCart")
@RequiredArgsConstructor

public class ShopingCartController {

    private final ShopingCartService shopingCartService;

    @PostMapping
    public ResponseEntity<ShopingCartDTO> crateteSopingCart(@Valid @RequestBody ShopingCartDTO dto){
        ShopingCartDTO newShopingCart = shopingCartService.createShopingCart(dto);
        return new ResponseEntity<>(newShopingCart, HttpStatus.CREATED);
    }
}
