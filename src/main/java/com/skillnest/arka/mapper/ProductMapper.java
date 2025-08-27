package com.skillnest.arka.mapper;

import com.skillnest.arka.dto.ProductDTO;
import com.skillnest.arka.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "category", target = "category")
    @Mapping(source = "brand", target = "brand")
    ProductDTO productToProductDTO(Product product);

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "brand", ignore = true)
    Product productDTOToProduct(ProductDTO productDTO);

    List<ProductDTO> productsToProductDtos(List<Product> products);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "brand", ignore = true)
    void updateProductFromDto(ProductDTO dto, @MappingTarget Product entity);

}
