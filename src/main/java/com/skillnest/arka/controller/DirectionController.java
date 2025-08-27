package com.skillnest.arka.controller;

import com.skillnest.arka.model.Direction;
import com.skillnest.arka.service.DirectionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/directions")
@RequiredArgsConstructor
public class DirectionController {
    private final DirectionService directionService;

    @GetMapping()
    public ResponseEntity<List<Direction>> getAllDirections(){
        List<Direction> directions = directionService.getDirections();
        return new ResponseEntity<>(directions, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Direction> createDirection(@Valid @RequestBody Direction direction){
        Direction newDirection= directionService.newDirection(direction);
        return  new ResponseEntity<>(newDirection, HttpStatus.CREATED);
    }
}
