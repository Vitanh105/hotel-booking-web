package com.backend.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AmenityDto {
    private String amenity;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
