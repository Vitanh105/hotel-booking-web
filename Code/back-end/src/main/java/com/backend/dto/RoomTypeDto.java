package com.backend.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class RoomTypeDto {

    private String type;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
