package com.backend.entity;

import jakarta.persistence.*;

import java.util.List;

@Table(name = "city")
@Entity
public class City {
    @Id
    @Column(name = "city_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city_name", unique = true, nullable = false)
    private String cityName;

    @OneToMany(mappedBy = "city")
    private List<Hotel> hotel;

    public City() {}

    public City(Long id, String cityName, List<Hotel> hotel) {
        this.id = id;
        this.cityName = cityName;
        this.hotel = hotel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<Hotel> getHotel() {
        return hotel;
    }

    public void setHotel(List<Hotel> hotel) {
        this.hotel = hotel;
    }
}
