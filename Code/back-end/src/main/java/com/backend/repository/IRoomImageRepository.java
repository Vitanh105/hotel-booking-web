package com.backend.repository;

import com.backend.entity.RoomImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoomImageRepository extends JpaRepository<RoomImage,Long> {
    Page<RoomImage> findAll(Specification<RoomImage> spec, Pageable pageable);
}
