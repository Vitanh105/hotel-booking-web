package com.backend.repository;

import com.backend.model.Hotel;
import com.backend.model.HotelDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHotelDetailRepository extends JpaRepository<HotelDetail, Long> {
    Page<HotelDetail> findAll(Specification<Hotel> specification, Pageable pageable);
}
