package com.backend.service.impl;

import com.backend.model.Booking;
import com.backend.model.Payment;
import com.backend.model.enums.Currency;
import com.backend.model.enums.PaymentMethod;
import com.backend.model.enums.PaymentStatus;
import com.backend.model.validate.BookingInitiationDTO;
import com.backend.responsitory.PaymentResponsitory;
import com.backend.service.PaymentService;
import org.springframework.stereotype.Service;
@Service
public class PaymentServiceImpl implements PaymentService {
    private PaymentResponsitory paymentResponsitory;
    public PaymentServiceImpl(PaymentResponsitory paymentResponsitory){
        this.paymentResponsitory= paymentResponsitory;
    }
    @Override
    public Payment savePayment(BookingInitiationDTO bookingInitiationDTO, Booking booking) {
        Payment payment = Payment.builder()
                                .booking(booking)
                                .amount(bookingInitiationDTO.getAmount())
                                .paymentMethod(PaymentMethod.VNPAY)
                                .paymentStatus(PaymentStatus.PENDING)
                                .currency(Currency.VND)
                                .build();
        return paymentResponsitory.save(payment);
    }
    
}
