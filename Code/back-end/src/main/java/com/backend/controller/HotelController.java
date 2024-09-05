package com.backend.controller;

import com.backend.dto.HotelDto;
import com.backend.form.HotelCreateForm;
import com.backend.form.HotelFilterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseStatus
public class HotelController {
    private final HotelService service;

    @Autowired
    public HotelController(HotelService service) {
        this.service = service;
    }

    @GetMapping("api/v1/hotels")
    public Page<HotelDto> findAll(
            HotelFilterForm form,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        Page<HotelDto> list = service.findAll(form, pageNo, pageSize, sortBy, sortDir);
        return list;
    }

    @GetMapping("api/v1/hotels/{id}")
    public HotelDto findById(Long id) {
        return service.findById(id);
    }

    @PostMapping("api/v1/hotels")
    @ResponseStatus(HttpStatus.CREATED)
    public HotelDto create(HotelCreateForm form) {
        return service.create(form);
    }

    @PutMapping("api/v1/hotels/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public HotelDto update(Long id, HotelCreateForm form) {
        return service.update(id, form);
    }

    @DeleteMapping("api/v1/hotels/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(Long id) {
        service.deleteById(id);
    }
}
