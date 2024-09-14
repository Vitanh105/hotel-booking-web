package com.backend.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PaymentMethodDto {
    private String name;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
