package com.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor // tạo constructor rỗng
@AllArgsConstructor // tạo constructor đầy đủ
@Builder    // builder déign pattern
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String addressLine;
    @Column(nullable = false)
    private String district;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String country;


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", addressLine='" + getAddressLine() + "'" +
            ", district='" + getDistrict() + "'" +
            ", city='" + getCity() + "'" +
            ", country='" + getCountry() + "'" +
            "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id) && Objects.equals(addressLine, address.addressLine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, addressLine);
    }
}
