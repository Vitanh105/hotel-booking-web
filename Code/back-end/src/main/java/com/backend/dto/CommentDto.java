package com.backend.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CommentDto {
    private String comment;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
