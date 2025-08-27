package com.skillnest.arka.controller;

import com.skillnest.arka.dto.PermitDTO;
import com.skillnest.arka.model.Permit;
import com.skillnest.arka.service.PermitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permits")
@RequiredArgsConstructor
public class PermitController{
    private  final PermitService permitService;

    @GetMapping
    public ResponseEntity<List<PermitDTO>> getPermits(){
        List<PermitDTO> permits = permitService.getPermits();
        return  new ResponseEntity<>(permits, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermitDTO> getPermitById(@PathVariable Long id){
        PermitDTO permitDTO = permitService.getPermitFindById(id);
        if (permitDTO == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(permitDTO, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<PermitDTO> createPermit (@Valid @RequestBody PermitDTO permitDTO){
        PermitDTO newPermit = permitService.newPermit(permitDTO);
        return new ResponseEntity<>(newPermit, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PermitDTO> updatePermit(@PathVariable Long id,@Valid @RequestBody PermitDTO permit){
        PermitDTO updatePermitDTO = permitService.updatePermit(id, permit);
        return  new ResponseEntity<>(updatePermitDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePermit (@PathVariable Long id){
        permitService.deletePermit(id);
        return ResponseEntity.noContent().build();
    }
}
