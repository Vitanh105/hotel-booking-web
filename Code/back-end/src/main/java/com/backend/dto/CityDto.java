package com.backend.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CityDto {
    private String cityName;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
