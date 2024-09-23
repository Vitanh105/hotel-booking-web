package com.backend.controller;

import com.backend.model.Image;
import com.backend.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/image")
public class ImageController {

    	@Autowired
	    private ImageService imageService;
     
	@PostMapping("/upload/hotel/{hotelId}")
    public ResponseEntity<List<Image>> uploadImageToHotel(@PathVariable Long hotelId, @RequestParam("files") MultipartFile[] files) throws IOException {
        List<Image> images = List.of(files).stream().map(file -> {
            Image image = new Image();
            try {
                image.setName(file.getOriginalFilename());
                image.setImage(file.getBytes());
                image.setType(file.getContentType());
                image.setRoom(null);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return image;
        }).collect(Collectors.toList());

        List<Image> savedImages = images.stream()
                                        .map(image -> imageService.addImageToHotel(hotelId, image))
                                        .collect(Collectors.toList());
                                        
        if (!savedImages.isEmpty()) {
            return ResponseEntity.ok(savedImages);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

	@PostMapping("/upload/room/{roomId}")
    public ResponseEntity<List<Image>>uploadImageToRoom(@PathVariable Long roomId, @RequestParam("files") MultipartFile[] files) throws IOException {
        List<Image> images = List.of(files).stream().map(file -> {
            Image image = new Image();
            try {
                image.setName(file.getOriginalFilename());
                image.setImage(file.getBytes());
                image.setType(file.getContentType());
                image.setHotel(null);
            } catch (IOException e) {
                // Handle exception
            }
            return image;
        }).collect(Collectors.toList());

        List<Image> savedImages = images.stream()
                                        .map(image -> imageService.addImageToRoom(roomId, image))
                                        .collect(Collectors.toList());
                                        
        if (!savedImages.isEmpty()) {
            return ResponseEntity.ok(savedImages);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/viewimagehotel/{hotelId}")
    public ResponseEntity<List<Image>> viewImages(@PathVariable Long hotelId) {
        List<Image> images = imageService.getImageByHotel(hotelId);
        if (!images.isEmpty()) {
            return ResponseEntity.ok(images);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

	@GetMapping("/view/{id}")
    public ResponseEntity<byte[]> viewImageById(@PathVariable Long id) {
        Optional<Image> imageData = imageService.getImageById(id);
        if (imageData.isPresent()) {
            Image image = imageData.get();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + image.getName() + "\"")
                    .contentType(MediaType.IMAGE_JPEG)  // Adjust according to your image type
                    .body(image.getImage());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

        @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImageById(@PathVariable Long id) {
        boolean isDeleted = imageService.deleteImageById(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
