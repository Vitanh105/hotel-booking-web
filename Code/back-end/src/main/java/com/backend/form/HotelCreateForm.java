package com.backend.form;

import com.backend.model.Hotel;
import lombok.Data;

import java.time.LocalDate;
@Data
public class HotelCreateForm {
    private String hotelName;
    private String address;
    private String description;
    private Hotel.Status status;
    private LocalDate createdAt;
    private LocalDate updatedAt;

}
