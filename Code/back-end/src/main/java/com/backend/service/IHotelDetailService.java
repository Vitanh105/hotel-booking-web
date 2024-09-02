package com.backend.service;

import com.backend.dto.HotelDetailDto;

import com.backend.form.HotelDetailCreateForm;
import com.backend.form.HotelDetailFilterForm;

import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

public interface IHotelDetailService {

    Page<HotelDetailDto> findAll(HotelDetailFilterForm form, int pageNo, int pageSize, String sortBy, String sortDir);

    @Transactional
    HotelDetailDto create(HotelDetailCreateForm form);

    HotelDetailDto findById(Long id);

    HotelDetailDto update(Long id, HotelDetailCreateForm form);

    void deleteById(Long id);
}
