package com.skillnest.arka.service;

import com.skillnest.arka.dto.CategoryDTO;
import com.skillnest.arka.mapper.CategoryMapper;
import com.skillnest.arka.model.Category;
import com.skillnest.arka.repository.CategoryRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class CategoryService {

    private  final CategoryMapper categoryMapper;
    private  final CategoryRepository categoryRepository;

    public List<CategoryDTO> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.categoryToCategoriesDTOs(categories);
    }

    public CategoryDTO getCategoryById(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("category not found"));

        return categoryMapper.categoryToCategoryDTO(category);
    }

    public Category getCategoryEntityById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id " + id));
    }

    public CategoryDTO createCategory(CategoryDTO categoryDTO){
        Category category = categoryMapper.categoryDTOToCategory(categoryDTO);
        categoryRepository.save(category);
        return  categoryMapper.categoryToCategoryDTO(category);
    }
}
