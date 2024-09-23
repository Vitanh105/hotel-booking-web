package com.backend.responsitory;


import com.backend.model.BookedRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookedRoomResponsitory extends JpaRepository<BookedRoom,Long> {

}
