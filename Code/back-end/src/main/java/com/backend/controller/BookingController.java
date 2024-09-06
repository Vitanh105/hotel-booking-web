package com.backend.controller;

import com.backend.dto.BookingDto;

import com.backend.form.BookingCreateForm;
import com.backend.form.BookingFilterForm;

import com.backend.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseStatus
public class BookingController {
    private final BookingService service;

    @Autowired
    public BookingController(BookingService service) {
        this.service = service;
    }

    @GetMapping("api/v1/bookings")
    public Page<BookingDto> findAll(
            BookingFilterForm form,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        Page<BookingDto> list = service.findAll(form, pageNo, pageSize, sortBy, sortDir);
        return list;
    }

    @GetMapping("api/v1/bookings/{id}")
    public BookingDto findById(Long id) {
        return service.findById(id);
    }

    @PostMapping("api/v1/bookings")
    @ResponseStatus(HttpStatus.CREATED)
    public BookingDto create(BookingCreateForm form) {
        return service.create(form);
    }

    @PutMapping("api/v1/bookings/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public BookingDto update(Long id, BookingCreateForm form) {
        return service.update(id, form);
    }

    @DeleteMapping("api/v1/bookings/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(Long id) {
        service.deleteById(id);
    }
}
