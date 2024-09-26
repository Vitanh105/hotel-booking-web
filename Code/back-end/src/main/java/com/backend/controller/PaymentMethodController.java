package com.backend.controller;

import com.backend.model.PaymentMethod;
import com.backend.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payment-methods")
public class PaymentMethodController {
    @Autowired
    private PaymentMethodService paymentMethodService;

    @GetMapping
    public ResponseEntity<List<PaymentMethod>> findAll() {
        List<PaymentMethod> paymentMethods = paymentMethodService.findAll();
        return ResponseEntity.ok(paymentMethods);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethod> findById(@PathVariable Long id) {
        Optional<PaymentMethod> paymentMethod = paymentMethodService.findById(id);
        return paymentMethod.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PaymentMethod> create(@RequestBody PaymentMethod paymentMethod) {
        PaymentMethod createdPaymentMethod = paymentMethodService.create(paymentMethod);
        return ResponseEntity.ok(createdPaymentMethod);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentMethod> updatePaymentMethod(@PathVariable Long id, @RequestBody PaymentMethod paymentMethodDetails) {
        PaymentMethod updatedPaymentMethod = paymentMethodService.update(id, paymentMethodDetails);
        if (updatedPaymentMethod != null) {
            return ResponseEntity.ok(updatedPaymentMethod);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        boolean isDeleted = paymentMethodService.deleteById(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
