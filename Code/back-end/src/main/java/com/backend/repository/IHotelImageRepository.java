package com.backend.repository;

import com.backend.entity.HotelImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHotelImageRepository extends JpaRepository <HotelImage, Long> {

}
