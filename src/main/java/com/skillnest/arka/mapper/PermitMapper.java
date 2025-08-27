package com.skillnest.arka.mapper;

import com.skillnest.arka.dto.PermitDTO;
import com.skillnest.arka.model.Permit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PermitMapper {

    //de entidad a dto
//    @Mapping(target = "roles", ignore = true)
    PermitDTO permitToPermitDTO(Permit permit);

    //esto se realiza para post o
//    @Mapping(target = "roles", ignore = true)
    Permit permitDTOToPermit (PermitDTO permitDTO);

    //get all
    //@Mapping(target = "roles", ignore = true)
    List<PermitDTO> permitToPermitDTOs(List<Permit> permits);

//    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updatePermitFromDTO(PermitDTO dto, @MappingTarget Permit permit);
}
