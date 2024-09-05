package com.backend.repository;

import com.backend.model.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHotelRepository extends JpaRepository<Hotel, Long > {
    Page<Hotel> findAll(Specification<Hotel> spec, Pageable pageable);
}
