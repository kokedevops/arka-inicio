package com.skillnest.arka.service;

import com.skillnest.arka.dto.BrandDTO;
import com.skillnest.arka.mapper.BrandMapper;
import com.skillnest.arka.model.Brand;
import com.skillnest.arka.repository.BrandRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandMapper brandMapper;
    private final BrandRepository brandRepository;

    public List<BrandDTO> getAllBrands(){
        List<Brand> brands = brandRepository.findAll();
        return brandMapper.brandToBrandDTOs(brands);
    }
    public Brand getBrandEntityById(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Brand not found with id " + id));
    }

    public BrandDTO createBrand (BrandDTO brandDTO){
        Brand brand = brandMapper.brandDTOToBrand(brandDTO);
        Brand savedBrand = brandRepository.save(brand);
        return  brandMapper.brandToBrandDTO(savedBrand);
    }
}
