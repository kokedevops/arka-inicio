package com.skillnest.arka.controller;

import com.skillnest.arka.dto.ShipmentDTO;
import com.skillnest.arka.service.ShimpmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/shipments")
@AllArgsConstructor
public class ShipmentController {

    private  final ShimpmentService shimpmentService;

    @GetMapping()
    public ResponseEntity<List<ShipmentDTO>> getAllShipments(){
        List<ShipmentDTO> shipmentDTOS = shimpmentService.getAllShipments();
        return  new ResponseEntity<>(shipmentDTOS, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ShipmentDTO> createShipment (@Valid @RequestBody ShipmentDTO shipmentDTO){
        ShipmentDTO newShipment = shimpmentService.createShipment(shipmentDTO);
        return new ResponseEntity<>(newShipment, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShipmentDTO> updateShipemnt(@PathVariable Long id, @Valid @RequestBody ShipmentDTO shipmentDTO){
        ShipmentDTO updateShipment = shimpmentService.updateShipment(id, shipmentDTO);
        if (updateShipment == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updateShipment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShipment (@PathVariable Long id){
        if (shimpmentService.deleteshipment(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
