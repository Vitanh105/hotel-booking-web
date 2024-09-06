package com.backend.service;

import com.backend.dto.AmenityDto;
import com.backend.dto.CityDto;
import com.backend.form.AmenityCreateForm;
import com.backend.form.AmenityFilterForm;
import com.backend.form.CityCreateForm;
import com.backend.form.CityFilterForm;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

public interface IAmenityService {

    Page<AmenityDto> findAll(AmenityFilterForm form, int pageNo, int pageSize, String sortBy, String sortDir);

    @Transactional
    AmenityDto create(AmenityCreateForm form);

    AmenityDto findById(Long id);

    AmenityDto update(Long id, AmenityCreateForm form);

    void deleteById(Long id);

}
