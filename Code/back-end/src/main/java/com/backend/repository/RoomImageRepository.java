package com.backend.repository;

import com.backend.model.RoomImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomImageRepository extends JpaRepository<RoomImage,Long> {
}
