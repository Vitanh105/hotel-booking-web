package com.backend.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RoomDto {
    private String roomName;
    private float price;
    private LocalDate createdAt;
    private LocalDate updatedAt;

}
