package com.backend.controller;

import com.backend.dto.HotelDto;
import com.backend.dto.PaymentMethodDto;
import com.backend.form.HotelCreateForm;
import com.backend.form.HotelFilterForm;
import com.backend.form.PaymentMethodCreateForm;
import com.backend.service.IPaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseStatus
public class PaymentMethodController {
private  final IPaymentMethodService service;
@Autowired
    public PaymentMethodController(IPaymentMethodService service) {
        this.service = service;
    }

    @GetMapping("api/v1/paymentMethods")
    public Page<PaymentMethodDto> findAll(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        Page<PaymentMethodDto> list = service.getAll(pageNo, pageSize, sortBy, sortDir);
        return list;
    }

    @GetMapping("api/v1/paymentMethods/{id}")
    public PaymentMethodDto findById(Long id) {
        return service.findById(id);
    }
    @PostMapping("api/v1/paymentMethods")
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentMethodDto create(PaymentMethodCreateForm form) {
        return service.create(form);
    }

    @PutMapping("api/v1/paymentMethods/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public PaymentMethodDto update(Long id, PaymentMethodCreateForm form) {
        return service.update(id, form);
    }

    @DeleteMapping("api/v1/paymentMethods/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(Long id) {
        service.deleteById(id);
    }

}
