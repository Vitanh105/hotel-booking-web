package com.backend.controller;
import com.backend.dto.HotelDetailDto;
import com.backend.form.HotelDetailCreateForm;
import com.backend.form.HotelDetailFilterForm;
import com.backend.service.HotelDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseStatus
public class HotelDetailController {
    private  final HotelDetailService service;

@Autowired
    public HotelDetailController(HotelDetailService service) {
        this.service = service;
    }
    @GetMapping("api/v1/hotelDetails")
    public Page<HotelDetailDto> findAll(
            HotelDetailFilterForm form,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        Page<HotelDetailDto> list = service.findAll(form, pageNo, pageSize, sortBy, sortDir);
        return list;
    }
    @GetMapping("api/v1/hotelDetails/{id}")
    public HotelDetailDto findById(Long id) {
        return service.findById(id);
    }
    @PostMapping("api/v1/hotelDetails")
    @ResponseStatus(HttpStatus.CREATED)
    public HotelDetailDto create(HotelDetailCreateForm form) {
        return service.create(form);
    }

    @PutMapping("api/v1/hotelDetails/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public HotelDetailDto update(Long id, HotelDetailCreateForm form) {
        return service.update(id, form);
    }

    @DeleteMapping("api/v1/hotelDetails/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(Long id) {
        service.deleteById(id);
    }
}
