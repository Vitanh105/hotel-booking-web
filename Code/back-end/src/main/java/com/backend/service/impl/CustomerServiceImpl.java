package com.backend.service.impl;

import com.backend.model.Customer;
import com.backend.responsitory.CustomerResponsitory;
import com.backend.service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerResponsitory customerResponsitory;

    @Autowired
    public CustomerServiceImpl(CustomerResponsitory customerResponsitory){
        this.customerResponsitory = customerResponsitory;
    }

    @Override
    public Optional<Customer> findByUserId(Long userId) {
        return customerResponsitory.findById(userId);
    }

    @Override
    public Optional<Customer> findByUsername(String username) {
        return customerResponsitory.findByUsername(username);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        customerResponsitory.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteByUserId(Long userId) {
        customerResponsitory.deleteByUserId(userId);
    }
    
}
