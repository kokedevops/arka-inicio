package com.skillnest.arka.service;

import com.skillnest.arka.dto.ShipmentDTO;
import com.skillnest.arka.mapper.ShipmentMapper;
import com.skillnest.arka.model.Direction;
import com.skillnest.arka.model.Shipment;
import com.skillnest.arka.repository.ShipmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShimpmentService {
    private  final ShipmentRepository shipmentRepository;
    private  final ShipmentMapper shipmentMapper;
    private  final DirectionService directionService;

    public List<ShipmentDTO> getAllShipments(){
        List<Shipment> shipmentDTOS = shipmentRepository.findAll();
        return shipmentMapper.shipmentToShipmentDTOs(shipmentDTOS);
    }

    public ShipmentDTO getShipmentById(Long id){
        Shipment shipment = shipmentRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("shipment not found"));
        return shipmentMapper.shipmentToShipmentDTO(shipment);
    }

    public Shipment getentityShipment(Long id){
        return shipmentRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("shipment not found"));
    }

    public ShipmentDTO createShipment (ShipmentDTO shipmentDTO){
        Shipment shipment = shipmentMapper.shipmentDTOToShipment(shipmentDTO);
        if (shipmentDTO.getDirection() != null && shipmentDTO.getDirection().getId() != null){
            Direction direction = directionService.getDirectionEntityById(shipmentDTO.getDirection().getId());
            shipment.setDirection(direction);
        }
        shipmentRepository.save(shipment);
        return shipmentMapper.shipmentToShipmentDTO(shipment);
    }

    public ShipmentDTO updateShipment (Long id, ShipmentDTO shipment){
        Shipment updateShipment = shipmentRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("shipment not found"));
        shipmentMapper.updateShipmentDTO(shipment, updateShipment);
        if (shipment.getDirection() != null && shipment.getDirection().getId() != null){
            Direction direction = directionService.getDirectionEntityById(shipment.getDirection().getId());
            updateShipment.setDirection(direction);
        }
        shipmentRepository.save(updateShipment);
        return shipmentMapper.shipmentToShipmentDTO(updateShipment);
    }

    public Boolean deleteshipment (Long id){
        Optional<Shipment> shipmentExist = shipmentRepository.findById(id);
        if (shipmentExist.isPresent()){
            shipmentRepository.deleteById(id);
            return  true;
        }
        return false;
    }
}
