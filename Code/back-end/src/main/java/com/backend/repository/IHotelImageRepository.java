package com.backend.repository;

import com.backend.model.HotelImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHotelImageRepository extends JpaRepository <HotelImage, Long> {

}
