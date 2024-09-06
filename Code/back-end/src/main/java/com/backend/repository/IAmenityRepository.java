package com.backend.repository;

import com.backend.entity.Amenity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAmenityRepository extends JpaRepository<Amenity,Long> {
    Page<Amenity> findAll(Specification<Amenity> amenity, Pageable pageable);
}
