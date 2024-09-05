package com.backend.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Table(name = "room")
@Entity
public class Room {
    @Id
    @Column(name = "room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @Column(name = "room_name", length = 500, nullable = false)
    private String roomName;

    @Column(name = "price", length = 50, nullable = false)
    private float price;

    @ManyToMany(mappedBy = "room")
    private Set<Amenity> amenities;

    @ManyToOne
    @JoinColumn(name = "roomType_id")
    private RoomType roomType;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "room")
    private List<RoomImage> imageId;

    @OneToMany(mappedBy = "room")
    private List<Booking> booking;

    @OneToMany(mappedBy = "room")
    private List<Comment> comment;

    public enum Status {
        AVAILABLE, NOT_AVAILABLE, MAINTENANCE
    }

    public Room(){}

    public Room(Long id, Hotel hotel, String roomName, float price, Set<Amenity> amenities, RoomType roomType, Status status, List<RoomImage> imageId, List<Booking> booking, List<Comment> comment) {
        this.id = id;
        this.hotel = hotel;
        this.roomName = roomName;
        this.price = price;
        this.amenities = amenities;
        this.roomType = roomType;
        this.status = status;
        this.imageId = imageId;
        this.booking = booking;
        this.comment = comment;
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

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Set<Amenity> getAmenities() {
        return amenities;
    }

    public void setAmenities(Set<Amenity> amenities) {
        this.amenities = amenities;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<RoomImage> getImageId() {
        return imageId;
    }

    public void setImageId(List<RoomImage> imageId) {
        this.imageId = imageId;
    }

    public List<Booking> getBooking() {
        return booking;
    }

    public void setBooking(List<Booking> booking) {
        this.booking = booking;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }
}
