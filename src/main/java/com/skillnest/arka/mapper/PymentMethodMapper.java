package com.skillnest.arka.mapper;

import com.skillnest.arka.dto.PymentMethodDTO;
import com.skillnest.arka.model.PymentMethod;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PymentMethodMapper {

    @Mapping(target = "details", source = "details")
    PymentMethodDTO pymentMethodToPymentMethodDTO(PymentMethod pymentMethod);

    @Mapping(target = "details", source = "details")
    PymentMethod pymentMethodDTOToPymentMethod(PymentMethodDTO pymentMethodDTO);

    List<PymentMethodDTO> pymentMethodToPymentMethodDTOs(List<PymentMethod> pymentMethods);

    @Mapping(target = "id", ignore = true)
    //@Mapping(target = "details", source = "details")
    void updatePymethodDTO(PymentMethodDTO pymentMethodDTO, @MappingTarget PymentMethod pymentMethod);
}
