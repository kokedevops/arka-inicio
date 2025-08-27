package com.skillnest.arka.mapper;

import com.skillnest.arka.dto.ShopingCartDTO;
import com.skillnest.arka.model.ShopingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShopingCartMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "status", target = "status")
    ShopingCartDTO ShopingCartToDTO(ShopingCart shopingCart);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "status", target = "status")
    ShopingCart shopingCartDTOToEntity(ShopingCartDTO shopingCartDTO);

    List<ShopingCartDTO> shopingCartsDTOs (List<ShopingCart> shopingCarts);

    @Mapping(target = "id", ignore = true)
    void updateShopingCartFromDTO(ShopingCart dto, @MappingTarget ShopingCart entity);
}
