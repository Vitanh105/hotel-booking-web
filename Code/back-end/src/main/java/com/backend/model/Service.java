package com.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    
    // @OneToMany(fetch = FetchType.LAZY, mappedBy = "service")
    // private List<RoomServiceModel> roomService;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
        name = "room_service",
        joinColumns = @JoinColumn(name="service_id"),
        inverseJoinColumns = @JoinColumn(name="room_id")
    )
    private List<Room> rooms = new ArrayList<>();
}
