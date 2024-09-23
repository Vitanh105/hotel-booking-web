package com.backend.service;

import com.backend.model.validate.HotelDTO;
import com.backend.model.validate.HotelRegistrationDTO;
import com.backend.model.Hotel;

import java.util.List;
import java.util.Optional;

public interface HotelService {
    Hotel saveHotel(HotelRegistrationDTO hotelRegistrationDTO);
    HotelDTO findHotelDtoByName(String name);
    HotelDTO findHotelDtoById(Long id);
    Optional<Hotel> findHotelById(Long id);
    List<HotelDTO> findAllHotels();
    HotelDTO updateHotel(HotelDTO hotelDTO);
    void deleteHotel(Long id);
    List<Hotel> findAllHotelsByManagerId(Long managerId);
    List<HotelDTO> findAllHotelDtosByManagerId(Long managerId);
    HotelDTO findHotelByIdAndManagerId(Long hotelId, Long managerId);
    HotelDTO updateHotelByManagerId(HotelDTO hotelDTO, Long managerId);
    void deleteHotelByIdAndManagerId(Long hotelId, Long managerID);
    HotelDTO mapHotelToHotelDto(Hotel hotel);
}
