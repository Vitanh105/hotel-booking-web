package com.backend.dto;

import com.backend.entity.Hotel;
import lombok.Data;

import java.time.LocalDate;
@Data
public class HotelDto {
    private String hotelName;
    private String address;
    private String description;
    private Hotel.Status status;
    private LocalDate createdAt;
    private LocalDate updatedAt;

}
