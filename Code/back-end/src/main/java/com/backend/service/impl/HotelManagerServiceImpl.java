package com.backend.service.impl;

import com.backend.model.HotelManager;
import com.backend.model.User;
import com.backend.responsitory.HotelManagerResponsitory;
import com.backend.service.HotelManagerService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class HotelManagerServiceImpl implements HotelManagerService {

    private HotelManagerResponsitory hotelManagerResponsitory;
    @Autowired
    public HotelManagerServiceImpl(HotelManagerResponsitory hotelManagerResponsitory){
        this.hotelManagerResponsitory= hotelManagerResponsitory;
    }
    @Override
    public HotelManager findByUser(User user) {
        return hotelManagerResponsitory.findByUser(user).orElseThrow(()-> new EntityNotFoundException("HotelManager not found for user"+ user.getUsername()));
    }
    @Override
    @Transactional
    public void deleteById(Long id) {
        hotelManagerResponsitory.deleteById(id);
    }
    @Override
    @Transactional
    public void deleteByUserId(Long userId) {
        hotelManagerResponsitory.deleteByUserId(userId);
    }
    
}
