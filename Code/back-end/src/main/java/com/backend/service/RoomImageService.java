package com.backend.service;

import com.backend.model.Room;
import com.backend.model.RoomImage;
import com.backend.repository.RoomImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class RoomImageService {

    @Autowired
    private RoomImageRepository roomImageRepository;

    public RoomImage createRoomImage(MultipartFile file, Room room) throws IOException {
        RoomImage roomImage = new RoomImage();
        roomImage.setRoom(room);
        roomImage.setImage(file.getBytes());

        return roomImageRepository.save(roomImage);
    }

    public List<RoomImage> getAllRoomImage() {
        return roomImageRepository.findAll();
    }

    public Optional<RoomImage> getRoomImageById(Long id) {
        return roomImageRepository.findById(id);
    }

    public RoomImage updateRoomImage(Long id, RoomImage newRoomImage) {
        return roomImageRepository.findById(id)
                .map(roomImage -> {
                    roomImage.setImage(newRoomImage.getImage());
                    return roomImageRepository.save(roomImage);
                })
                .orElse(null);
    }

    public void deleteRoomImage(Long id) {
        roomImageRepository.deleteById(id);
    }
}
