package com.skillnest.arka.mapper;

import com.skillnest.arka.dto.ShopingCartDetailDTO;
import com.skillnest.arka.model.ShopingCart;
import com.skillnest.arka.model.ShopingCartDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShopingCartDetailMapper {

    @Mapping(source =  "product.id", target = "productId")
    @Mapping(source = "shopingCart.id", target = "cartId")
    @Mapping(source = "product.name", target = "productName")
    ShopingCartDetailDTO shopingCartDetailToDTO(ShopingCartDetail shopingCartDetail);

    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "cartId", target = "shopingCart.id")
    @Mapping(source = "productName", target = "product.name")
    ShopingCartDetail shopingCartDetailDTOToEntity(ShopingCartDetailDTO dto);

    List<ShopingCartDetailDTO> shopingCartDetailToDTOs(List<ShopingCartDetail> shopingCartDetails);

    @Mapping(target = "id", ignore = true)
    void updateShpingCartDetailFromDTO(ShopingCartDetailDTO dto, @MappingTarget ShopingCartDetail entity);
}
