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

    @Lob
    @Column(name = "image", columnDefinition = "BLOB")
    private byte[] image;

    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt;

    @Column(name = "updated_at", updatable = true)
    private LocalDate updatedAt;

    public HotelImage() {
    }

    public HotelImage(Long id, Hotel hotel, byte[] image, LocalDate createdAt, LocalDate updatedAt) {
        this.id = id;
        this.hotel = hotel;
        this.image = image;
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

    public byte[] getImage() {
        return image;
    }
    public void setImage(byte[] image) {
        this.image = image;
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
