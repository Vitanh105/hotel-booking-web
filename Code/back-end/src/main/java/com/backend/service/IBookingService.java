package com.backend.service;

import com.backend.dto.BookingDto;
import com.backend.dto.HotelDto;
import com.backend.form.BookingCreateForm;
import com.backend.form.BookingFilterForm;
import com.backend.form.HotelCreateForm;
import com.backend.form.HotelFilterForm;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

public interface IBookingService {
    Page<BookingDto> findAll(BookingFilterForm form, int pageNo, int pageSize, String sortBy, String sortDir);
    @Transactional
    BookingDto create(BookingCreateForm form);
    BookingDto findById(Long id);
    BookingDto update(Long id,BookingCreateForm form);
    void deleteById(Long id);
}
