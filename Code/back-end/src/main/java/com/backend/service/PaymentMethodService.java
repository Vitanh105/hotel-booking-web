package com.backend.service;

import com.backend.model.PaymentMethod;
import com.backend.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodService {
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    public List<PaymentMethod> findAll() {
        return paymentMethodRepository.findAll();
    }

    public PaymentMethod create(PaymentMethod paymentMethod) {
        return paymentMethodRepository.save(paymentMethod);
    }

    public Optional<PaymentMethod> findById(Long id) {
        return paymentMethodRepository.findById(id);
    }

    public PaymentMethod update(Long id, PaymentMethod paymentMethodDetails) {
        Optional<PaymentMethod> optionalPaymentMethod = paymentMethodRepository.findById(id);
        if (optionalPaymentMethod.isPresent()) {
            PaymentMethod existingPaymentMethod = optionalPaymentMethod.get();
            existingPaymentMethod.setName(paymentMethodDetails.getName());
            return paymentMethodRepository.save(existingPaymentMethod);
        }
        return null;
    }

    public boolean deleteById(Long id) {
        if (paymentMethodRepository.existsById(id)) {
            paymentMethodRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
