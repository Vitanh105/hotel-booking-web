package com.backend.service;

import com.backend.model.Amenity;

import com.backend.repository.AmenityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AmenityService {
    @Autowired
    private AmenityRepository amenityRepository;

    public List<Amenity> findAll() {
        return amenityRepository.findAll();
    }

    public Amenity create(Amenity amenity) {
        return amenityRepository.save(amenity);
    }

    public Optional<Amenity> findById(Long id) {
        return amenityRepository.findById(id);
    }

    public Amenity update(Long id, Amenity amenityDetails) {
        Optional<Amenity> amenityOptional = amenityRepository.findById(id);
        if (amenityOptional.isPresent()) {
            Amenity amenity = amenityOptional.get();
            amenity.setAmenity(amenityDetails.getAmenity());
//            amenity.setRoom(amenityDetails.getRoom());
            return amenityRepository.save(amenity);
        } else {
            throw new RuntimeException("Amenity not found with id " + id);
        }
    }

    public void deleteById(Long id) {
        if (amenityRepository.existsById(id)) {
            amenityRepository.deleteById(id);
        } else {
            throw new RuntimeException("Amenity not found with id " + id);
        }
    }
}
