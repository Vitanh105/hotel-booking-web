package com.backend.service;

import com.backend.model.Booking;
import com.backend.model.validate.BookingDTO;
import com.backend.model.validate.BookingInitiationDTO;

import java.util.List;

public interface BookingService {
    
    Booking saveBooking(BookingInitiationDTO bookingInitiationDTO, Long userId);
    
    BookingDTO confirmBooking(BookingInitiationDTO bookingInitiationDTO, Long userId);
    
    List<BookingDTO> findAllBooking();
    
    BookingDTO findBookingById(Long bookingId);
    
    List<BookingDTO> findBookingsByCustomerId(Long customerId);
    
    BookingDTO findBookingByIdAndCustomerId(Long bookingId, Long customerId);
    
    List<BookingDTO> findBookingByManagerId(Long managerId);
    
    BookingDTO fingBookingByIdAndManagerId(Long bookingId, Long managerId);
    
    BookingDTO mapBookingModelToBookingDto(Booking booking);
    
}
