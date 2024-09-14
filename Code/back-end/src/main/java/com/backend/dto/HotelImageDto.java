package com.backend.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class HotelImageDto {
    private String imageUrl;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
