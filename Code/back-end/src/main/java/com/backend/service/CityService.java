package com.backend.service;


import com.backend.model.City;

import com.backend.repository.CityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public List<City> findAll() {
        return cityRepository.findAll();
    }

    public City create(City city) {
        return cityRepository.save(city);
    }

    public Optional<City> findById(Long id) {
        return cityRepository.findById(id);
    }

    public City update(Long id, City cityDetails) {
        Optional<City> cityOptional = cityRepository.findById(id);
        if (cityOptional.isPresent()) {
            City city = cityOptional.get();
            city.setCityName(cityDetails.getCityName());
            city.setHotel(cityDetails.getHotel());
            return cityRepository.save(city);
        } else {
            throw new RuntimeException("City not found with id " + id);
        }
    }

    public void deleteById(Long id) {
        if (cityRepository.existsById(id)) {
            cityRepository.deleteById(id);
        } else {
            throw new RuntimeException("City not found with id " + id);
        }
    }
}
