package com.backend.controller;

import com.backend.model.Room;
import com.backend.model.RoomImage;
import com.backend.service.RoomImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/room-images")
public class RoomImageController {
    @Autowired
    private RoomImageService roomImageService;

    @PostMapping("/upload")
    public ResponseEntity<RoomImage> uploadRoomImage(@RequestParam("image") MultipartFile file, @RequestParam("roomId") Long roomId) {
        try {
            Room room = new Room();  // Assuming room object is retrieved using roomId (you would need to implement RoomService for this)
            room.setId(roomId);
            RoomImage roomImage = roomImageService.saveRoomImage(file, room);
            return new ResponseEntity<>(roomImage, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getRoomImage(@PathVariable Long id) {
        return roomImageService.getRoomImage(id).map(roomImage -> {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "image/jpeg");
            return new ResponseEntity<>(roomImage.getImage(), headers, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoomImage(@PathVariable Long id) {
        roomImageService.deleteRoomImage(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
