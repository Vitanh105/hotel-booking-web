package com.backend.service;

import com.backend.model.Booking;
import com.backend.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public Booking create(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Optional<Booking> findById(Long id) {
        return bookingRepository.findById(id);
    }

    public Booking update(Long id, Booking booking) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (optionalBooking.isPresent()) {
            Booking existingBooking = optionalBooking.get();
            existingBooking.setHotel(booking.getHotel());
            existingBooking.setRoom(booking.getRoom());
            existingBooking.setUser(booking.getUser());
            existingBooking.setPaymentMethod(booking.getPaymentMethod());
            existingBooking.setCheckInDate(booking.getCheckInDate());
            existingBooking.setCheckOutDate(booking.getCheckOutDate());
            existingBooking.setTotalAmount(booking.getTotalAmount());
            return bookingRepository.save(existingBooking);
        }
        return null;
    }

    public boolean deleteById(Long id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
