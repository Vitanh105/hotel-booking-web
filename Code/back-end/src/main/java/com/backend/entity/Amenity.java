package com.backend.entity;

import jakarta.persistence.*;

import java.util.Set;

//Tiện nghi phòng
@Table(name = "amenity")
@Entity
public class Amenity {
    @Id
    @Column(name = "amenity_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String amenity;

    @ManyToMany
    @JoinTable(name = "room_amenity",
            joinColumns = @JoinColumn(name = "amenity_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id"))
    private Set<Room> room;

    public Amenity() {}

    public Amenity(Long id, String amenity, Set<Room> room) {
        this.id = id;
        this.amenity = amenity;
        this.room = room;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAmenity() {
        return amenity;
    }

    public void setAmenity(String amenity) {
        this.amenity = amenity;
    }

    public Set<Room> getRoom() {
        return room;
    }

    public void setRoom(Set<Room> room) {
        this.room = room;
    }
}
