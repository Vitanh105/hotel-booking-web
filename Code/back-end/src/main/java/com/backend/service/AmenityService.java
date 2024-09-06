package com.backend.service
        ;

import com.backend.dto.AmenityDto;

import com.backend.entity.Amenity;

import com.backend.form.AmenityCreateForm;
import com.backend.form.AmenityFilterForm;
import com.backend.repository.IAmenityRepository;
import com.backend.specification.AmenitySpecification;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class AmenityService implements IAmenityService {
    @Autowired
    public AmenityService(IAmenityRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    private final IAmenityRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public Page<AmenityDto> findAll(AmenityFilterForm form, int pageNo, int pageSize, String sortBy, String sortDir) {
        Specification<Amenity> amenity = AmenitySpecification.buildSpec(form);
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Amenity> amenityPage = repository.findAll(amenity, pageable);
        return amenityPage.map(amenity1 -> modelMapper.map(amenity1, AmenityDto.class));
    }

    @Override
    public AmenityDto create(AmenityCreateForm form) {
        var amenity = modelMapper.map(form, Amenity.class);
        var saveAmenity = repository.save(amenity);
        return modelMapper.map(saveAmenity, AmenityDto.class);
    }

    @Override
    public AmenityDto findById(Long id) {
        return repository.findById(id)
                .map(amenity -> modelMapper.map(amenity, AmenityDto.class)).orElse(null);
    }

    @Override
    public AmenityDto update(Long id, AmenityCreateForm form) {
        var amenity = repository.findById(id).orElse(null);
        modelMapper.map(form, amenity);
        var saveAmenity = repository.save(amenity);
        return modelMapper.map(saveAmenity, AmenityDto.class);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);

    }
}
