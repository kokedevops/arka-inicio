package com.skillnest.arka.service;

import com.skillnest.arka.model.Brand;
import com.skillnest.arka.model.Direction;
import com.skillnest.arka.repository.DirectionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectionService {
    private final DirectionRepository directionRepository;

    public List<Direction> getDirections(){
        return directionRepository.findAll();
    }

    public Direction getDirectionEntityById(Long id) {
        return directionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("direction not found with id " + id));
    }

    public Direction newDirection(Direction direction){
        return directionRepository.save(direction);
    }
}
