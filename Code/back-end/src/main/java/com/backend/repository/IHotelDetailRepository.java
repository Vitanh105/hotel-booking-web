package com.backend.repository;

import com.backend.entity.Hotel;
import com.backend.entity.HotelDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHotelDetailRepository extends JpaRepository<HotelDetail, Long> {
    Page<HotelDetail> findAll(Specification<Hotel> specification, Pageable pageable);
}
