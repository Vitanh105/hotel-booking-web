package com.backend.service;

import com.backend.model.Booking;
import com.backend.model.Payment;
import com.backend.model.validate.BookingInitiationDTO;

public interface PaymentService {
    
    Payment savePayment(BookingInitiationDTO bookingInitiationDTO, Booking booking);
}
