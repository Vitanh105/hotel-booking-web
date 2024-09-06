package com.backend.service;

import com.backend.exception.ResourceNotFoundException;
import com.backend.entity.Amenity;
import com.backend.repository.AmenityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AmenityService {
    @Autowired
    private AmenityRepository amenityRepository;

    public Page<Amenity> getAllAmenities(Pageable pageable) {
        return amenityRepository.findAll(pageable);
    }

    public Optional<Amenity> getAmenityById(Long id) {
        return amenityRepository.findById(id);
    }

    public Amenity createAmenity(Amenity amenity) {
        return amenityRepository.save(amenity);
    }

    public Amenity updateAmenity(Long id, Amenity amenityDetails) {
        Amenity amenity = amenityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Amenity not found"));
        amenity.setAmenity(amenityDetails.getAmenity());
        return amenityRepository.save(amenity);
    }

    public void deleteAmenity(Long id) {
        Amenity amenity = amenityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Amenity not found"));
        amenityRepository.delete(amenity);
    }
}
