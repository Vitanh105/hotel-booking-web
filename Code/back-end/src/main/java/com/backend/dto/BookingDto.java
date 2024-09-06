package com.backend.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class BookingDto {
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private float totalAmount;
}
