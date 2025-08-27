package com.skillnest.arka.service;

import com.skillnest.arka.dto.PermitDTO;
import com.skillnest.arka.mapper.PermitMapper;
import com.skillnest.arka.model.Brand;
import com.skillnest.arka.model.Permit;
import com.skillnest.arka.repository.PermitRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PermitService {
    private final PermitRepository permitRepository;
    private final PermitMapper permitMapper;

    public List<PermitDTO> getPermits(){
        List<Permit> permits = permitRepository.findAll();
        return permitMapper.permitToPermitDTOs(permits);
    }

    public PermitDTO getPermitFindById(Long id){
        Permit permit = permitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("permit not found with id: " + id));
        return permitMapper.permitToPermitDTO(permit);
    }

    public Permit getPermitEntityById(Long id) {
        return permitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Permit not found with id " + id));
    }

    public PermitDTO newPermit(PermitDTO permitDTO){
        Permit newPermit = permitMapper.permitDTOToPermit(permitDTO);
        permitRepository.save(newPermit);
        return  permitMapper.permitToPermitDTO(newPermit);
    }

    public PermitDTO updatePermit (Long idPermit, PermitDTO permitDTO){
        Permit existingPermit = permitRepository.findById(idPermit)
                .orElseThrow(() -> new EntityNotFoundException("Permit not found"));
        permitMapper.updatePermitFromDTO(permitDTO, existingPermit);
        Permit updatedPermit = permitRepository.save(existingPermit);

        return  permitMapper.permitToPermitDTO(updatedPermit);
    }
    public void deletePermit(Long idPermit){
        Permit existingPermit = permitRepository.findById(idPermit)
                .orElseThrow(() -> new EntityNotFoundException("Permit not found"));
        permitRepository.delete(existingPermit);
    }

}
