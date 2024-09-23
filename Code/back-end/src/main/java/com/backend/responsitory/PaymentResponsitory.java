package com.backend.responsitory;

import com.backend.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PaymentResponsitory extends JpaRepository<Payment,Long> {

    Payment findByTransactionId(String transactionId);
}
