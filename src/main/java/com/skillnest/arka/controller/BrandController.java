package com.skillnest.arka.controller;

import com.skillnest.arka.dto.BrandDTO;
import com.skillnest.arka.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @GetMapping
    public ResponseEntity<List<BrandDTO>> getAllBrandDTO(){
        List<BrandDTO> brandDTOS = brandService.getAllBrands();
        return new ResponseEntity<>(brandDTOS, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BrandDTO> createBrand (@Valid @RequestBody BrandDTO brandDTO){
        BrandDTO newBrand = brandService.createBrand(brandDTO);
        return new ResponseEntity<>(newBrand, HttpStatus.CREATED);
    }
}
