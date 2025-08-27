package com.skillnest.arka.service;

import com.skillnest.arka.dto.PymentMethodDTO;
import com.skillnest.arka.mapper.PymentMethodMapper;
import com.skillnest.arka.model.Product;
import com.skillnest.arka.model.PymentMethod;
import com.skillnest.arka.repository.PymentMethodRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PymentMethodService {

    private final PymentMethodRepository pymentMethodRepository;
    private final PymentMethodMapper pymentMethodMapper;

    public List<PymentMethodDTO> getAllPymentMethods(){
        List<PymentMethod> pymentMethodList = pymentMethodRepository.findAll();
        return pymentMethodMapper.pymentMethodToPymentMethodDTOs(pymentMethodList);
    }

    public PymentMethod getPymentmethodEntity (Long id){
        return pymentMethodRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("pyment method not found"));
    }

    public PymentMethodDTO createPymentMethod(PymentMethodDTO pymentMethodDTO){
        PymentMethod pymentMethod = pymentMethodMapper.pymentMethodDTOToPymentMethod(pymentMethodDTO);
        PymentMethod createdPymentMethod = pymentMethodRepository.save(pymentMethod);
        return pymentMethodMapper.pymentMethodToPymentMethodDTO(createdPymentMethod);
    }

    public  PymentMethodDTO updatePymentMethod (Long id, PymentMethodDTO pymentMethodDTO){
        PymentMethod pymentMethod = pymentMethodRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Pyment method not found"));
        pymentMethodMapper.updatePymethodDTO(pymentMethodDTO, pymentMethod);
        PymentMethod updatedPymentMethod = pymentMethodRepository.save(pymentMethod);
        return pymentMethodMapper.pymentMethodToPymentMethodDTO(updatedPymentMethod);
    }

    public Boolean deletePymentMethod (Long id){
        Optional<PymentMethod> existPymentMethod= pymentMethodRepository.findById(id);
        if (existPymentMethod.isPresent()){
            pymentMethodRepository.deleteById(id);
            return  true;
        }
        return false;
    }

}
