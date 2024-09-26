package com.backend.service;

import com.backend.model.Room;
import com.backend.model.RoomImage;
import com.backend.repository.RoomImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class RoomImageService {

    @Autowired
    private RoomImageRepository roomImageRepository;

    public RoomImage saveRoomImage(MultipartFile file, Room room) throws IOException {
        RoomImage roomImage = new RoomImage();
        roomImage.setRoom(room);
        roomImage.setImage(file.getBytes());

        return roomImageRepository.save(roomImage);
    }

    public Optional<RoomImage> getRoomImage(Long id) {
        return roomImageRepository.findById(id);
    }

    public void deleteRoomImage(Long id) {
        roomImageRepository.deleteById(id);
    }
}
