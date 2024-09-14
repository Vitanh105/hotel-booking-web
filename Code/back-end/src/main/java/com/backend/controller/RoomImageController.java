package com.backend.controller;

import com.backend.dto.HotelDto;
import com.backend.dto.RoomImageDto;
import com.backend.form.HotelCreateForm;
import com.backend.form.HotelFilterForm;
import com.backend.form.RoomImageCreateForm;
import com.backend.form.RoomImageFilterForm;
import com.backend.service.RoomImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseStatus
public class RoomImageController {
    private  final RoomImageService service;
@Autowired
    public RoomImageController(RoomImageService service) {
        this.service = service;
    }


    @GetMapping("api/v1/roomImages")
    public Page<RoomImageDto> findAll(
            RoomImageFilterForm form,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        Page<RoomImageDto> list = service.findAll(form, pageNo, pageSize, sortBy, sortDir);
        return list;
    }
    @GetMapping("api/v1/roomImages/{id}")
    public RoomImageDto findById(Long id) {
        return service.findById(id);
    }

    @PostMapping("api/v1/roomImages")
    @ResponseStatus(HttpStatus.CREATED)
    public RoomImageDto create(RoomImageCreateForm form) {
        return service.create(form);
    }

    @PutMapping("api/v1/roomImages/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public RoomImageDto update(Long id, RoomImageCreateForm form) {
        return service.update(id, form);
    }

    @DeleteMapping("api/v1/roomImages/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(Long id) {
        service.deleteById(id);
    }

}
