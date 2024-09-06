package com.backend.controller;

import com.backend.dto.CommentDto;
import com.backend.dto.HotelDto;
import com.backend.form.CommentCreateForm;
import com.backend.form.CommentFilterForm;
import com.backend.form.HotelCreateForm;
import com.backend.form.HotelFilterForm;
import com.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseStatus
public class CommentController {

    private final CommentService service;
@Autowired
    public CommentController(CommentService service) {
        this.service = service;
    }
    @GetMapping("api/v1/comments")
    public Page<CommentDto> findAll(
            CommentFilterForm form,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        Page<CommentDto> list = service.findAll(form, pageNo, pageSize, sortBy, sortDir);
        return list;
    }
    @GetMapping("api/v1/comments/{id}")
    public CommentDto findById(Long id) {
        return service.findById(id);
    }
    @PostMapping("api/v1/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto create(CommentCreateForm form) {
        return service.create(form);
    }


    @PutMapping("api/v1/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CommentDto update(Long id, CommentCreateForm form) {
        return service.update(id, form);
    }

    @DeleteMapping("api/v1/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(Long id) {
        service.deleteById(id);
    }
}
