package com.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Table(name = "room_type")
@Entity
public class RoomType {
    @Id
    @Column(name = "room_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "roomType")
    private List<Room> room;

    public RoomType() {}

    public RoomType(Long id, String type, List<Room> room) {
        this.id = id;
        this.type = type;
        this.room = room;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Room> getRoom() {
        return room;
    }

    public void setRoom(List<Room> room) {
        this.room = room;
    }
}
