package com.backend.service;

import com.backend.model.Customer;

import java.util.Optional;

public interface CustomerService {
    Optional<Customer> findByUserId(Long userId);
    Optional<Customer> findByUsername(String username);
    void deleteById(Long id);
    public void deleteByUserId(Long userId);
}
