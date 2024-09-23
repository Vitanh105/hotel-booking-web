package com.backend.model;

import com.backend.model.enums.StarRating;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hotel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL,orphanRemoval = true)
    @Builder.Default
    private List<Room> rooms = new ArrayList<>();

    @OneToMany(mappedBy = "hotel")
    private List<Booking> bookings = new ArrayList<>();

    @ManyToOne
    @JoinColumn(nullable = false)
    private HotelManager hotelManager;

    
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Image> images = new ArrayList<>();
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StarRating starRating;

    @Column(columnDefinition = "LONGTEXT")
    private String description ;


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            ", rooms='" + getRooms() + "'" +
            ", bookings='" + getBookings() + "'" +
            ", hotelManager='" + getHotelManager() + "'" +
            ", starRating='" + getStarRating() + "'" +
            // ", image='" + getImage() + "'" +
            "}";
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return Objects.equals(id, hotel.id) && Objects.equals(name, hotel.name) && Objects.equals(hotelManager, hotel.hotelManager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, hotelManager);
    }
}
