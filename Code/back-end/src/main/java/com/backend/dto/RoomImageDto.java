package com.backend.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RoomImageDto {
    private String imageUrl;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
