package com.skillnest.arka.controller;

import com.skillnest.arka.dto.CategoryDTO;
import com.skillnest.arka.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategoriesDTO (){
        List<CategoryDTO> categoryDTOS= categoryService.getAllCategories();
        return new ResponseEntity<>(categoryDTOS, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory (@Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO newCategory = categoryService.createCategory(categoryDTO);
        return  new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }
}
