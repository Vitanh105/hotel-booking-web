package com.backend.service;

import com.backend.model.Room;
import com.backend.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public Room create(Room room) {
        return roomRepository.save(room);
    }

    public Optional<Room> findById(Long id) {
        return roomRepository.findById(id);
    }

    public Room update(Long id, Room room) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()) {
            Room existingRoom = optionalRoom.get();
            existingRoom.setRoomName(room.getRoomName());
            existingRoom.setPrice(room.getPrice());
            existingRoom.setStatus(room.getStatus());
            existingRoom.setRoomType(room.getRoomType());
            existingRoom.setHotel(room.getHotel());
            existingRoom.setAmenities(room.getAmenities());
            existingRoom.setImageId(room.getImageId());
            existingRoom.setBooking(room.getBooking());
            existingRoom.setComment(room.getComment());
            return roomRepository.save(existingRoom);
        }
        return null;
    }

    public boolean deleteById(Long id) {
        if (roomRepository.existsById(id)) {
            roomRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
