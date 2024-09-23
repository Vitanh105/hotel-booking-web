package com.backend.service;


import com.backend.model.HotelManager;
import com.backend.model.User;

public interface HotelManagerService {
    HotelManager findByUser(User user);
    void deleteById(Long id);
    
    public void deleteByUserId(Long userId);
}
