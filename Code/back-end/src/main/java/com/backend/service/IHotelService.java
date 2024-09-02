package com.backend.service;

import com.backend.dto.HotelDto;
import com.backend.form.HotelCreateForm;
import com.backend.form.HotelFilterForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface IHotelService {
    Page<HotelDto> findAll(HotelFilterForm form,  int pageNo, int pageSize, String sortBy, String sortDir);
    @Transactional
    HotelDto create(HotelCreateForm form);
    HotelDto findById(Long id);
    HotelDto update(Long id,HotelCreateForm form);
    void deleteById(Long id);
}
