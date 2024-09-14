package com.backend.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HotelDetailDto {
    private int quantity;
    private String information;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
