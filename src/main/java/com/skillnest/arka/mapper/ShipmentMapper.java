package com.skillnest.arka.mapper;

import com.skillnest.arka.dto.ShipmentDTO;
import com.skillnest.arka.model.Shipment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShipmentMapper {
    ShipmentDTO shipmentToShipmentDTO(Shipment shipment);
    Shipment shipmentDTOToShipment(ShipmentDTO shipmentDTO);
    List<ShipmentDTO> shipmentToShipmentDTOs(List<Shipment> shipments);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "direction", ignore = true)
    void updateShipmentDTO(ShipmentDTO dto, @MappingTarget Shipment shipment);

}
