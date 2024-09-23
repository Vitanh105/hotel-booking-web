package com.backend.responsitory;

import com.backend.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RoomResponsitory extends JpaRepository<Room,Long> {
    
}
