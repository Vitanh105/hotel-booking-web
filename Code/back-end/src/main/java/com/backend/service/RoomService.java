package com.backend.service;

import com.backend.model.Hotel;
import com.backend.model.Room;
import com.backend.model.validate.RoomDTO;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    Room saveRoom (RoomDTO roomDTO, Hotel hotel);
    List<Room> saveRooms(List<RoomDTO> roomDTOs, Hotel hotel);
    Optional<Room> findRoomById (Long id);
    
    Room updateRoom(RoomDTO roomDTO);
    void deleteRoom (Long id);
    Room mapRoomDtoToRoom(RoomDTO roomDTO, Hotel hotel);
    RoomDTO mapRoomTRoomDto(Room room);
}
