package com.backend.repository;

import com.backend.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaymentMethodRepository extends JpaRepository<PaymentMethod,Long> {
}
