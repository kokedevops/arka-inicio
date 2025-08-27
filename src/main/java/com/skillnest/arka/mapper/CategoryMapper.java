package com.skillnest.arka.mapper;

import com.skillnest.arka.dto.CategoryDTO;
import com.skillnest.arka.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    //mapeo de la entidad a DTO
    CategoryDTO categoryToCategoryDTO(Category category);

    //mapeo para creación o actualización
    Category categoryDTOToCategory(CategoryDTO categoryDTO);

    List<CategoryDTO> categoryToCategoriesDTOs(List<Category> categories);
}
