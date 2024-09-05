package com.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "hotel_Detail")
public class HotelDetail {
    @Column(name = "hotel_detail_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;


    @Column(name = "quantity", length = 500, nullable = false)
    private int quantity;
    @Column(name = "information", length = 500, nullable = false)
    private String information;

}
