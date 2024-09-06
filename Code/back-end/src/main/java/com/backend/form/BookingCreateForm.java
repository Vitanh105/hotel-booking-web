package com.backend.form;

import lombok.Data;

import java.time.LocalDate;
@Data
public class BookingCreateForm {
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private float totalAmount;
}
