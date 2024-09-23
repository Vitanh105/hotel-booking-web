package com.backend.controller;

import com.backend.exception.HotelAlreadyExistsException;
import com.backend.model.Hotel;
import com.backend.model.validate.BookingDTO;
import com.backend.model.validate.HotelDTO;
import com.backend.model.validate.HotelRegistrationDTO;
import com.backend.service.BookingService;
import com.backend.service.HotelService;
import com.backend.service.ImageService;
import com.backend.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


@RestController

@RequestMapping("/manager")
public class ManagerController {
    private HotelService hotelService;
    private UserService userService ;
    private BookingService bookingService;
    private ImageService imageService;
    @Autowired
    public ManagerController(HotelService hotelService,
                             UserService userService, 
                             BookingService bookingService,
                             ImageService imageService){
        this.bookingService = bookingService;
        this.userService = userService;
        this.hotelService= hotelService;
        this.imageService=imageService;
    }
    
    // thêm mới hotel
    @PostMapping("/hotels")
    public ResponseEntity<?> addHotel(@Valid @RequestBody HotelRegistrationDTO hotelRegistrationDTO,
                                             BindingResult result
                                              ){
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
        }
        try {
            Hotel hotel = hotelService.saveHotel(hotelRegistrationDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(hotel.getRooms());
        } catch (HotelAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    // lấy id của hotelmanager đang đang nhập
    private long getCurrentManagerId(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findUserByUsername(username).getHotelManager().getId();
    }
    // lấy tất cả khách sạn mà manager này quản lý
    @GetMapping("/hotels")
    public ResponseEntity<List<HotelDTO>> listHotels(){
        Long managerId = getCurrentManagerId();
        try {
            List<HotelDTO> listHotels = hotelService.findAllHotelDtosByManagerId(managerId);
            return ResponseEntity.ok(listHotels);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }


    @PutMapping("/hotels/{id}")
    public ResponseEntity<?> editHotel (@PathVariable Long id, @Valid @RequestBody HotelDTO hotelDTO, BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
        }
        try {
            Long managerId = getCurrentManagerId();
            hotelDTO.setId(id);
            hotelService.updateHotelByManagerId(hotelDTO, managerId);
            return ResponseEntity.ok("Hotel updated successfully");
        }
        catch (HotelAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
         catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hotel not found");
        }
    }

    @DeleteMapping("/hotels/{id}")
    public ResponseEntity<?> deleteHotel(@PathVariable Long id){
        Long managerId = getCurrentManagerId();
        hotelService.deleteHotelByIdAndManagerId(id, managerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    // Lấy danh sách đặt chỗ
    @GetMapping("/bookings")
    public ResponseEntity<?> listBookings() {
        try {
            Long managerId = getCurrentManagerId();
            List<BookingDTO> bookingDTOs = bookingService.findBookingByManagerId(managerId);
            return ResponseEntity.ok(bookingDTOs);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bookings not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    // xem chi tiet dat phong theo id
    @GetMapping("/bookings/{id}")
    public ResponseEntity<?> bookingDetail(@PathVariable Long id){
        try {
            Long managerId = getCurrentManagerId();
            BookingDTO bookingDTO = bookingService.fingBookingByIdAndManagerId(id, managerId);
            LocalDate checkinDate = bookingDTO.getCheckinDate();
            LocalDate checkoutDate = bookingDTO.getCheckoutDate();
            Long durationDays = ChronoUnit.DAYS.between(checkinDate, checkoutDate);
    
            bookingDTO.setDurationDays(durationDays);
            return ResponseEntity.ok(bookingDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }    
}
