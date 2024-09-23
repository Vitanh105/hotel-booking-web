package com.backend.controller;

import com.backend.model.Customer;
import com.backend.model.validate.BookingDTO;
import com.backend.service.BookingService;
import com.backend.service.CustomerService;
import com.backend.service.UserService;
import com.backend.util.JwtTokenUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController

@RequestMapping("/customer")
public class CustomerController {
    private UserService userService;
    private BookingService bookingService;
    private JwtTokenUtil jwtTokenUtil;
    private HttpServletRequest request;
    private CustomerService customerService;

    @Autowired
    public CustomerController(UserService userService,
                            BookingService bookingService,
                            JwtTokenUtil jwtTokenUtil,
                            HttpServletRequest request,
                            CustomerService customerService){
        this.bookingService=bookingService;
        this.userService=userService;
        this.jwtTokenUtil=jwtTokenUtil;
        this.request=request;
        this.customerService=customerService;
    }
    // lấy khach hang id hien tai dang dang nhap
    private Long getCurrentCustomerId(){
        String jwt = jwtTokenUtil.extractJwtFromRequest(request);
        String username = jwtTokenUtil.getUsernameFromToken(jwt);
        Customer customer = customerService.findByUsername(username).orElseThrow();
        System.out.println(customer.getId());
        return customer.getId();
    }

    @GetMapping("/bookings")
    public ResponseEntity<?> listBookings(){
        try {
            Long customerId = getCurrentCustomerId();
            List<BookingDTO> bookingDTOs = bookingService.findBookingsByCustomerId(customerId);
            System.out.println(customerId);
            return ResponseEntity.ok(bookingDTOs);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ôi! Lỗi rồi. Vui lòng thử lại");
        }
    }

    @GetMapping("/bookings/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable Long id){
        try {
            Long customerId = getCurrentCustomerId();
            BookingDTO bookingDTO = bookingService.findBookingByIdAndCustomerId(id, customerId);
            LocalDate checkinDate = bookingDTO.getCheckinDate();
            LocalDate checkoutDate = bookingDTO.getCheckoutDate();
            long durationDays = ChronoUnit.DAYS.between(checkinDate,checkoutDate);
            bookingDTO.setDurationDays(durationDays); // lay so ngay luu tru
            return ResponseEntity.ok(bookingDTO);
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booing not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ôi! Lỗi rồi. Vui lòng thử lại");
        }
    }
}
