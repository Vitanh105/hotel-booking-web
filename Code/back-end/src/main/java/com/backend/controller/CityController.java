package com.backend.controller;

import com.backend.dto.CityDto;

import com.backend.form.CityCreateForm;
import com.backend.form.CityFilterForm;

import com.backend.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseStatus
public class CityController {
    private final CityService service;

    @Autowired
    public CityController(CityService service) {
        this.service = service;
    }

    @GetMapping("api/v1/cities")
    public Page<CityDto> findAll(
            CityFilterForm form,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        Page<CityDto> list = service.findAll(form, pageNo, pageSize, sortBy, sortDir);
        return list;
    }

    @GetMapping("api/v1/cities/{id}")
    public CityDto findById(Long id) {
        return service.findById(id);
    }

    @PostMapping("api/v1/cities")
    @ResponseStatus(HttpStatus.CREATED)
    public CityDto create(CityCreateForm form) {
        return service.create(form);
    }

    @PutMapping("api/v1/cities/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CityDto update(Long id, CityCreateForm form) {
        return service.update(id, form);
    }

    @DeleteMapping("api/v1/cities/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(Long id) {
        service.deleteById(id);
    }
}
