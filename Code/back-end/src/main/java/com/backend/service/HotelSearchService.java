package com.backend.service;

import com.backend.model.Hotel;
import com.backend.model.validate.HotelAvailabilityDTO;

import java.time.LocalDate;
import java.util.List;

public interface HotelSearchService {

    // tìm kiếm danh sách hotel khả dụng theo thành phố và ngày checkin checkout
    List<HotelAvailabilityDTO> findAvailableHotelsByCityAndDate(String city, LocalDate checkinDate, LocalDate checkoutDate);

    HotelAvailabilityDTO findAvailableHotelById(Long hotelId, LocalDate checkinDate, LocalDate checkoutDate);

    HotelAvailabilityDTO mapHotelToHotelAvailabilityDto(Hotel hotel, LocalDate checkinDate, LocalDate checkoutDate);


}
