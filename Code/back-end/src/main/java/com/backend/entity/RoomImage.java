package com.backend.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Table(name = "room_image")
@Entity
public class RoomImage {
    @Id
    @Column(name = "room_image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(name = "image_url", length = 500, nullable = false)
    private String imageUrl;

    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt;

    @Column(name = "updated_at", updatable = true)
    private LocalDate updatedAt;

    public RoomImage() {}

    public RoomImage(Long id, Room room, String imageUrl, LocalDate createdAt, LocalDate updatedAt) {
        this.id = id;
        this.room = room;
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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
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
