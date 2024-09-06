package com.backend.service;

import com.backend.dto.CityDto;

import com.backend.dto.HotelDto;
import com.backend.entity.City;

import com.backend.form.CityCreateForm;
import com.backend.form.CityFilterForm;
import com.backend.repository.ICityRepository;
import com.backend.specification.CitySpecification;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service

public class CityService implements ICityService{


    private final ICityRepository repository;
    private final ModelMapper modelMapper;
    @Autowired
    public CityService(ICityRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }
    @Override
    public Page<CityDto> findAll(CityFilterForm form, int pageNo, int pageSize, String sortBy, String sortDir) {
       Specification<City> city=CitySpecification.buildSpec(form);
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<City> cityPage = repository.findAll(city,pageable);
        return cityPage.map(city1 -> modelMapper.map(city1, CityDto.class));
    }

    @Override
    public CityDto create(CityCreateForm form) {
        var city =modelMapper.map(form,City.class);
        var saveCity= repository.save(city);
        return modelMapper.map(saveCity, CityDto.class);
    }

    @Override
    public CityDto findById(Long id) {
        return repository.findById(id)
                .map(city -> modelMapper.map(city, CityDto.class)).orElse(null);
    }

    @Override
    public CityDto update(Long id, CityCreateForm form) {
        var city =repository.findById(id).orElse(null);
        modelMapper.map(form,city);
        var saveCity=repository.save(city);
        return  modelMapper.map(saveCity, CityDto.class);

    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);

    }
}
