package com.backend.repository;

import com.backend.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoomRepository extends JpaRepository<Room,Long> {
    Page<Room> findAll(Specification<Room> spec, Pageable pageable);
}
