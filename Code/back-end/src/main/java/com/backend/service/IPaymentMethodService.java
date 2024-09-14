package com.backend.service;

import com.backend.dto.HotelDto;
import com.backend.dto.PaymentMethodDto;
import com.backend.form.HotelCreateForm;
import com.backend.form.HotelFilterForm;
import com.backend.form.PaymentMethodCreateForm;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

public interface IPaymentMethodService {
    Page<PaymentMethodDto> getAll(int pageNo, int pageSize, String sortBy, String sortDir);
    @Transactional
    PaymentMethodDto create(PaymentMethodCreateForm form);
    PaymentMethodDto findById(Long id);
    PaymentMethodDto update(Long id,PaymentMethodCreateForm form);
    void deleteById(Long id);
}
