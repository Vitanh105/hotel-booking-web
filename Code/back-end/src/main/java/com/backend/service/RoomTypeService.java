package com.backend.service;

import com.backend.model.RoomType;
import com.backend.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomTypeService{
    @Autowired
    RoomTypeRepository roomTypeRepository;

    public List<RoomType> findAll() {
        return roomTypeRepository.findAll();
    }

    public RoomType create(RoomType roomType) {
        return roomTypeRepository.save(roomType);
    }

    public Optional<RoomType> findById(Long id) {
        return roomTypeRepository.findById(id);
    }

    public RoomType update(Long id, RoomType roomTypeDetails) {
        Optional<RoomType> optionalRoomType = roomTypeRepository.findById(id);
        if (optionalRoomType.isPresent()) {
            RoomType existingRoomType = optionalRoomType.get();
            existingRoomType.setType(roomTypeDetails.getType());
            return roomTypeRepository.save(existingRoomType);
        }
        return null;
    }

    public boolean deleteById(Long id) {
        if (roomTypeRepository.existsById(id)) {
            roomTypeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
