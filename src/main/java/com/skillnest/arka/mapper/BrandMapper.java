package com.skillnest.arka.mapper;

import com.skillnest.arka.dto.BrandDTO;
import com.skillnest.arka.model.Brand;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    //mapeo de la entidad a DTO
    BrandDTO brandToBrandDTO(Brand brand);

    //para post o put
    Brand brandDTOToBrand(BrandDTO brandDTO);

    //get all o listar las brands
    List<BrandDTO> brandToBrandDTOs(List<Brand> brands);
}
