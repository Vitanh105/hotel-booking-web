package com.backend.controller;

import com.backend.dto.AmenityDto;

import com.backend.form.AmenityCreateForm;
import com.backend.form.AmenityFilterForm;

import com.backend.service.AmenityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseStatus
public class AmenityController {
    @Autowired
    public AmenityController(AmenityService service) {
        this.service = service;
    }

    private final AmenityService service;
    @GetMapping("api/v1/amenities")
    public Page<AmenityDto> findAll(
            AmenityFilterForm form,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        Page<AmenityDto> list = service.findAll(form, pageNo, pageSize, sortBy, sortDir);
        return list;
    }

    @GetMapping("api/v1/amenities/{id}")
    public AmenityDto findById(Long id) {
        return service.findById(id);
    }


    @PostMapping("api/v1/amenities")
    @ResponseStatus(HttpStatus.CREATED)
    public AmenityDto create(AmenityCreateForm form) {
        return service.create(form);
    }

    @PutMapping("api/v1/amenities/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public AmenityDto update(Long id, AmenityCreateForm form) {
        return service.update(id, form);
    }

    @DeleteMapping("api/v1/amenities/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(Long id) {
        service.deleteById(id);
    }
}
