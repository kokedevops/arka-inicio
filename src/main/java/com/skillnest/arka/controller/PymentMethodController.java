package com.skillnest.arka.controller;

import com.skillnest.arka.dto.PymentMethodDTO;
import com.skillnest.arka.service.PymentMethodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/pymentMethods")
@RequiredArgsConstructor
public class PymentMethodController {

    private final PymentMethodService pymentMethodService;

    @GetMapping()
    public ResponseEntity<List<PymentMethodDTO>> getAllPymentMethods() {
        List<PymentMethodDTO> pymentMethodDTOS = pymentMethodService.getAllPymentMethods();
        return new ResponseEntity<>(pymentMethodDTOS, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<PymentMethodDTO> createPymentMethod(@Valid @RequestBody PymentMethodDTO pymentMethodDTO) {
        PymentMethodDTO createPymentMethod = pymentMethodService.createPymentMethod(pymentMethodDTO);
        return new ResponseEntity<>(createPymentMethod, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PymentMethodDTO> updatePymentMethod (@PathVariable Long id,@Valid @RequestBody PymentMethodDTO pymentMethodDTO){
        PymentMethodDTO updatePymentMethod = pymentMethodService.updatePymentMethod(id, pymentMethodDTO);
        if (updatePymentMethod == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(updatePymentMethod, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePymentMethod (@PathVariable Long id){
        if (pymentMethodService.deletePymentMethod(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
