package com.backend.service;

import com.backend.dto.HotelImageDto;
import com.backend.form.HotelImageCreateForm;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

public interface IHotelImageService {
@Transactional
void create(HotelImageCreateForm form);
HotelImageDto getById(Long id);
void updateById(Long id,HotelImageDto form);
void deleteById(Long id);
}
