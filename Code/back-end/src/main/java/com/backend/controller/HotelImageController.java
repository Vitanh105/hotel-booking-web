package com.backend.controller;

import com.backend.model.Hotel;
import com.backend.model.HotelImage;
import com.backend.service.HotelImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/hotel-images")
public class HotelImageController {
    @Autowired
    private HotelImageService hotelImageService;

    @PostMapping("/upload")
    public ResponseEntity<HotelImage> uploadHotelImage(@RequestParam("image") MultipartFile file, @RequestParam("hotelId") Long hotelId) {
        try {
            Hotel hotel = new Hotel();  // Assuming room object is retrieved using roomId (you would need to implement RoomService for this)
            hotel.setId(hotelId);
            HotelImage hotelImage = hotelImageService.createHotelImage(file, hotel);
            return new ResponseEntity<>(hotelImage, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getHotelImage(@PathVariable Long id) {
        return hotelImageService.getHotelImageById(id).map(hotelImage -> {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "image/jpeg");
            return new ResponseEntity<>(hotelImage.getImage(), headers, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HotelImage> updateHotelImage(@PathVariable Long id, @RequestBody HotelImage newHotelImage) {
        HotelImage updatedHotelImage = hotelImageService.updateHotelImage(id, newHotelImage);
        return updatedHotelImage != null ? new ResponseEntity<>(updatedHotelImage, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotelImage(@PathVariable Long id) {
        hotelImageService.deleteHotelImage(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
