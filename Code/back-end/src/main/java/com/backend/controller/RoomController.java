package com.backend.controller;

import com.backend.dto.CommentDto;
import com.backend.dto.RoomDto;
import com.backend.form.CommentCreateForm;
import com.backend.form.CommentFilterForm;
import com.backend.form.RoomCreateForm;
import com.backend.form.RoomFilterForm;
import com.backend.service.IRoomService;
import com.backend.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseStatus
public class RoomController {
    private  final RoomService service;
@Autowired
    public RoomController(RoomService service) {
        this.service = service;
    }
    @GetMapping("api/v1/rooms")
    public Page<RoomDto> findAll(
            RoomFilterForm form,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        Page<RoomDto> list = service.findAll(form, pageNo, pageSize, sortBy, sortDir);
        return list;
    }

    @GetMapping("api/v1/rooms/{id}")
    public RoomDto findById(Long id) {
        return service.findById(id);
    }

    @PostMapping("api/v1/rooms")
    @ResponseStatus(HttpStatus.CREATED)
    public RoomDto create(RoomCreateForm form) {
        return service.create(form);
    }

    @PutMapping("api/v1/rooms/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public RoomDto update(Long id, RoomCreateForm form) {
        return service.update(id, form);
    }

    @DeleteMapping("api/v1/rooms/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(Long id) {
        service.deleteById(id);
    }



}
