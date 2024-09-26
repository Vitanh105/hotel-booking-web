package com.backend.service;

import com.backend.model.Hotel;
import com.backend.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    public Hotel create(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public Optional<Hotel> findById(Long id) {
        return hotelRepository.findById(id);
    }

    public Hotel update(Long id, Hotel hotel) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isPresent()) {
            Hotel existingHotel = optionalHotel.get();
            existingHotel.setHotelName(hotel.getHotelName());
            existingHotel.setCity(hotel.getCity());
            existingHotel.setAddress(hotel.getAddress());
            existingHotel.setDescription(hotel.getDescription());
            existingHotel.setStatus(hotel.getStatus());
            return hotelRepository.save(existingHotel);
        }
        return null;
    }

    public boolean deleteById(Long id) {
        if (hotelRepository.existsById(id)) {
            hotelRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
