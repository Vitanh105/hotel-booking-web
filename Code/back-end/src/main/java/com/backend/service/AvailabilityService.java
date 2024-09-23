package com.backend.service;

import com.backend.model.validate.RoomSelectionDTO;

import java.time.LocalDate;
import java.util.List;

public interface AvailabilityService {
    // lấy số lượng phòng trống 
    Integer getMinAvailableRooms(Long id, LocalDate checkinDate, LocalDate checkoutDate);

    // cập nhật phòng trống
    void updateAvailabilities(Long hotelId, LocalDate checkinDate, LocalDate checkoutDate, List<RoomSelectionDTO> roomSelections);
}
