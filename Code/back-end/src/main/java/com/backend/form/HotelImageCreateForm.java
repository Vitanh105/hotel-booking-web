package com.backend.form;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HotelImageCreateForm {
    private String imageUrl;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
