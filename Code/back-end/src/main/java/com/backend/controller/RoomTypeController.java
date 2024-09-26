package com.backend.controller;


import com.backend.model.RoomType;
import com.backend.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room-types")
public class RoomTypeController {
    @Autowired
    private RoomTypeService roomTypeService;

    @GetMapping
    public ResponseEntity<List<RoomType>> findAll() {
        List<RoomType> roomTypes = roomTypeService.findAll();
        return ResponseEntity.ok(roomTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomType> findById(@PathVariable Long id) {
        Optional<RoomType> roomType = roomTypeService.findById(id);
        return roomType.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RoomType> create(@RequestBody RoomType roomType) {
        RoomType createdRoomType = roomTypeService.create(roomType);
        return ResponseEntity.ok(createdRoomType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomType> update(@PathVariable Long id, @RequestBody RoomType roomTypeDetails) {
        RoomType updatedRoomType = roomTypeService.update(id, roomTypeDetails);
        if (updatedRoomType != null) {
            return ResponseEntity.ok(updatedRoomType);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoomType(@PathVariable Long id) {
        boolean isDeleted = roomTypeService.deleteById(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
