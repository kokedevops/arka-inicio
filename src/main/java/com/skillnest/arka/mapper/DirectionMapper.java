package com.skillnest.arka.mapper;

import com.skillnest.arka.dto.DirectionDTO;
import com.skillnest.arka.model.Direction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DirectionMapper {
    DirectionDTO directionToDirectionDTO(Direction direction);
    Direction directioDTOToDirection(DirectionDTO directionDTO);
    List<DirectionDTO> directionToDirectionDTOs(List<Direction> directions);

    @Mapping(target = "id", ignore = true)
    void updateDirectionFromDTO(DirectionDTO dto, @MappingTarget Direction direction);
}
