package com.backend.controller;

import com.backend.exception.HotelAlreadyExistsException;
import com.backend.exception.UsernameAlreadyExistsException;
import com.backend.model.Customer;
import com.backend.model.HotelManager;
import com.backend.model.enums.RoleType;
import com.backend.model.validate.BookingDTO;
import com.backend.model.validate.HotelDTO;
import com.backend.model.validate.UserDTO;
import com.backend.responsitory.CustomerResponsitory;
import com.backend.responsitory.HotelManagerResponsitory;
import com.backend.service.*;
import com.backend.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    private HotelService hotelService;
    private BookingService bookingService;
    private CustomerResponsitory customerResponsitory;
    private HotelManagerResponsitory hotelManagerResponsitory;
    private HotelManagerService hotelManagerService;
    private CustomerService customerService;


    @Autowired
    public AdminController(UserService userService, 
                            HotelService hotelService, 
                            BookingService bookingService,
                            CustomerResponsitory customerResponsitory,
                            HotelManagerResponsitory hotelManagerResponsitory,
                            HotelManagerService hotelManagerService,
                            CustomerService customerService){
        this.bookingService=bookingService;
        this.userService=userService;
        this.hotelService=hotelService;
        this.customerResponsitory=customerResponsitory;
        this.hotelManagerResponsitory = hotelManagerResponsitory;
        this.hotelManagerService=hotelManagerService;
        this.customerService=customerService;
    }

    // load all user
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> listUsers(){
        List<UserDTO> userDTOs = userService.findAllUsers();
        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        try {
            UserDTO userDTO = userService.findUserById(id);
            return ResponseEntity.ok(userDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDTO> editUserById(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO, BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        try {
        // Lấy thông tin người dùng hiện tại
        UserDTO currentUserDTO = userService.findUserById(id);
        RoleType currentRoleType = currentUserDTO.getRole().getRoleType();

        // Cập nhật thông tin người dùng
        userService.updateUser(userDTO);

        // Kiểm tra và cập nhật quyền
        RoleType newRoleType = userDTO.getRole().getRoleType();
        if (newRoleType != currentRoleType) {
            if (newRoleType == RoleType.CUSTOMER) {
                // Xóa khỏi bảng HotelManager nếu tồn tại
                hotelManagerService.deleteByUserId(id);
                // Thêm vào bảng Customer
                Customer customer = new Customer();
                customer.setId(userDTO.getId());
                customer.setUser(userService.findUserByUsername(userDTO.getUsername()));
                customerResponsitory.save(customer);
                
            }else if (newRoleType == RoleType.MANAGER) {
                // Xóa khỏi bảng Customer nếu tồn tại
                customerService.deleteByUserId(id);
                // Thêm vào bảng HotelManager
                HotelManager manager = new HotelManager();
                manager.setId(userDTO.getId());
                manager.setUser(userService.findUserByUsername(userDTO.getUsername()));
                hotelManagerResponsitory.save(manager);
            }
        }
        
        return ResponseEntity.ok(userDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (UsernameAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

        @GetMapping("/hotels")
    public ResponseEntity<List<HotelDTO>> listHotels() {
        List<HotelDTO> hotelDTOList = hotelService.findAllHotels();
        return ResponseEntity.ok(hotelDTOList);
    }

    @GetMapping("/hotels/{id}")
    public ResponseEntity<HotelDTO> getHotelById(@PathVariable Long id) {
        try {
            HotelDTO hotelDTO = hotelService.findHotelDtoById(id);
            return ResponseEntity.ok(hotelDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/hotels/{id}")
        public ResponseEntity<HotelDTO> editHotel(
        @PathVariable Long id,
        @Valid @RequestBody HotelDTO hotelDTO,
        BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        try {
            hotelService.updateHotel(hotelDTO);
            return ResponseEntity.ok(hotelDTO);
        } catch (HotelAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }
    @DeleteMapping("/hotels/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }

     @GetMapping("/bookings")
    public ResponseEntity<List<BookingDTO>> listBookings() {
        List<BookingDTO> bookingDTOList = bookingService.findAllBooking();
        return ResponseEntity.ok(bookingDTOList);
    }

    @GetMapping("/bookings/{id}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long id) {
        try {
            BookingDTO bookingDTO = bookingService.findBookingById(id);
            return ResponseEntity.ok(bookingDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
