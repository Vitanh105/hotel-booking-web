package com.backend.controller;


import com.backend.dto.RoomTypeDto;

import com.backend.form.RoomTypeCreateForm;
import com.backend.form.RoomTypeFilterForm;
import com.backend.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseStatus
public class RoomTypeController {


    private final RoomTypeService service;
@Autowired
    public RoomTypeController(RoomTypeService service) {
        this.service = service;
    }

    @GetMapping("api/v1/roomTypes")
    public Page<RoomTypeDto> findAll(
            RoomTypeFilterForm form,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        Page<RoomTypeDto> list = service.findAll(form, pageNo, pageSize, sortBy, sortDir);
        return list;
    }

    @GetMapping("api/v1/roomTypes/{id}")
    public RoomTypeDto findById(Long id) {
        return service.findById(id);
    }


    @PostMapping("api/v1/roomTypes")
    @ResponseStatus(HttpStatus.CREATED)
    public RoomTypeDto create(RoomTypeCreateForm form) {
        return service.create(form);
    }


    @PutMapping("api/v1/roomTypes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public RoomTypeDto update(Long id, RoomTypeCreateForm form) {
        return service.update(id, form);
    }


    @DeleteMapping("api/v1/roomTypes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(Long id) {
        service.deleteById(id);
    }


}
