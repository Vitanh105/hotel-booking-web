package com.backend.model.validate;

import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class BookingInitiationDTO {
    private Long hotelId;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private Long durationDays ;// số ngày khách ở khách sạn
    private List<RoomSelectionDTO> roomSelections = new ArrayList<>();
    private double amount;
}
