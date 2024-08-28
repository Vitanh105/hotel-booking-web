package com.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Table(name = "payment_method")
@Entity
public class PaymentMethod {
    @Column(name = "payment_method_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100, nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "paymentMethod")
    private List<Booking> booking;

    public PaymentMethod() {}

    public PaymentMethod(Long id, String name, List<Booking> booking) {
        this.id = id;
        this.name = name;
        this.booking = booking;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Booking> getBooking() {
        return booking;
    }

    public void setBooking(List<Booking> booking) {
        this.booking = booking;
    }
}
