package com.backend.responsitory;

import com.backend.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ImageResponsitory extends JpaRepository<Image,Long> {
    List<Image> findByHotelId(Long hotelId);
}
