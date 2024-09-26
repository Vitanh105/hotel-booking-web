package com.backend.repository;

import com.backend.model.RoomType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoomTypeRepository extends JpaRepository<RoomType,Long> {
    Page<RoomType> findAll(Specification<RoomType> spec, Pageable pageable);
}
