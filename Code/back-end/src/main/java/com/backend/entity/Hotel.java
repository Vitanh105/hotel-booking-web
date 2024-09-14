package com.backend.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.List;

@Table(name = "hotel")
@Entity
public class Hotel {
    @Column(name = "hotel_id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hotel_name", length = 255, nullable = false)
    private String hotelName;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "address", length = 255, nullable = false)
    private String address;

    @Column(name = "description", length = 500, nullable = false)
    private String description;

    @Column(name = "status", insertable = false, updatable = true)
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "hotel")
    private List<Room> room;

    @OneToMany(mappedBy = "hotel")
    private List<HotelImage> imageId;

    @OneToMany(mappedBy = "hotel")
    private List<Booking> booking;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDate createdAt;

    @Column(name = "updated_at", updatable = true)
    @UpdateTimestamp
    private LocalDate updatedAt;

    public enum Status {
        ACTIVE, INACTIVE
    }

    public Hotel() {}

    public Hotel(Long id, String hotelName, City city, String address, String description, Status status, List<Room> room, List<HotelImage> imageId, List<Booking> booking, LocalDate createdAt, LocalDate updatedAt) {
        this.id = id;
        this.hotelName = hotelName;
        this.city = city;
        this.address = address;
        this.description = description;
        this.status = status;
        this.room = room;
        this.imageId = imageId;
        this.booking = booking;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Room> getRoom() {
        return room;
    }

    public void setRoom(List<Room> room) {
        this.room = room;
    }

    public List<HotelImage> getImageId() {
        return imageId;
    }

    public void setImageId(List<HotelImage> imageId) {
        this.imageId = imageId;
    }

    public List<Booking> getBooking() {
        return booking;
    }

    public void setBooking(List<Booking> booking) {
        this.booking = booking;
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
