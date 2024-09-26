package com.backend.controller;

import com.backend.model.Amenity;
import com.backend.service.AmenityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/amenities")
public class AmenityController {
    @Autowired
    private AmenityService amenityService;

    @GetMapping
    public ResponseEntity<List<Amenity>> findAll() {
        List<Amenity> amenities = amenityService.findAll();
        return ResponseEntity.ok(amenities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Amenity> findById(@PathVariable Long id) {
        Optional<Amenity> amenityOptional = amenityService.findById(id);
        return amenityOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Amenity> create(@RequestBody Amenity amenity) {
        Amenity createdAmenity = amenityService.create(amenity);
        return ResponseEntity.ok(createdAmenity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Amenity> update(@PathVariable Long id, @RequestBody Amenity amenityDetails) {
        try {
            Amenity updatedAmenity = amenityService.update(id, amenityDetails);
            return ResponseEntity.ok(updatedAmenity);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            amenityService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
