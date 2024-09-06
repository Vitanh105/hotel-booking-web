package com.backend.service;

import com.backend.dto.CityDto;
import com.backend.dto.HotelDetailDto;
import com.backend.form.CityCreateForm;
import com.backend.form.CityFilterForm;
import com.backend.form.HotelDetailCreateForm;
import com.backend.form.HotelDetailFilterForm;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

public interface ICityService {

    Page<CityDto> findAll(CityFilterForm form, int pageNo, int pageSize, String sortBy, String sortDir);

    @Transactional
    CityDto create(CityCreateForm form);

    CityDto findById(Long id);

    CityDto update(Long id, CityCreateForm form);

    void deleteById(Long id);

}
