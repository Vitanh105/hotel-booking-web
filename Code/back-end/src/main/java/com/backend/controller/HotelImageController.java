package com.backend.controller;

import com.backend.dto.HotelImageDto;
import com.backend.form.HotelImageCreateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class HotelImageController {


    private final IHotelImageService service;

    @Autowired

    public HotelImageController(IHotelImageService service) {
        this.service = service;
    }

    @GetMapping("api/v1/hotelImages")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(HotelImageCreateForm form) {
        service.create(form);
    }

    @GetMapping("api/v1/hotelImages/{id}")
    public HotelImageDto findById(Long id) {
        return service.getById(id);
    }

    @PutMapping("api/v1/hotelImages/{id}")
    public void update(Long id, HotelImageDto form) {
        service.updateById(id, form);
    }

    @DeleteMapping("api/v1/hotelImages/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(Long id) {
        service.deleteById(id);
    }
}
