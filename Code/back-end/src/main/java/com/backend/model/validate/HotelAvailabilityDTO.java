package com.backend.model.validate;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelAvailabilityDTO {
    private Long id;
    private String name;
    private AddressDTO addressDTO;
    private List<RoomDTO> roomDTOs = new ArrayList<>();
    private Integer maxAvailableSingleRooms; // số lượng phòng còn
    private Integer maxAvailableDoubleRooms;
    private Integer maxAvailableFamilyRooms;
    private List<ImageDTO> imageDTOs;
    private String description ;
    
}
