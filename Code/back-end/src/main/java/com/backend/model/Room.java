package com.backend.model;

import com.backend.model.enums.RoomType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Hotel hotel;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;
    
    
    private int roomCount;

    
    private double pricePerNight;
    
    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL,orphanRemoval = true)
    @Builder.Default
    private List<Availability> availabilities = new ArrayList<>();
    
    // @OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
    // private List<RoomServiceModel> roomService;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
        name = "room_service",
        joinColumns = @JoinColumn(name="room_id"),
        inverseJoinColumns = @JoinColumn(name="service_id")
    )
    private List<Service> services= new ArrayList<>();

    
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Image> images =new ArrayList<>();
    


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", hotel='" + getHotel() + "'" +
            ", roomType='" + getRoomType() + "'" +
            ", roomNumber='" + getRoomCount() + "'" +
            ", pricePerNight='" + getPricePerNight() + "'" +
            // ", image='" + getImage() + "'" +
            ", availabilities='" + getAvailabilities() + "'" +
            "}";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(id, room.id) && Objects.equals(hotel, room.hotel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hotel);
    }


}
