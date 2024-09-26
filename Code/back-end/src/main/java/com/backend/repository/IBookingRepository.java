package com.backend.repository;

import com.backend.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookingRepository extends JpaRepository<Booking,Long> {
    Page<Booking> findAll(Specification<Booking> spec, Pageable pageable);
}
