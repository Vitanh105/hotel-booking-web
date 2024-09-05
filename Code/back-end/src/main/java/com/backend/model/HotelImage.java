package com.backend.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Table(name = "hotel_image")
@Entity
public class HotelImage {
    @Column(name = "hotel_image_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @Column(name = "image_url", length = 500, nullable = false)
    private String imageUrl;

    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt;

    @Column(name = "updated_at", updatable = true)
    private LocalDate updatedAt;

    public HotelImage() {}

    public HotelImage(Long id, Hotel hotel, String imageUrl, LocalDate createdAt, LocalDate updatedAt) {
        this.id = id;
        this.hotel = hotel;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }
}
