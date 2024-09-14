package com.backend.service;

import com.backend.dto.HotelDto;
import com.backend.dto.RoomDto;
import com.backend.form.HotelCreateForm;
import com.backend.form.HotelFilterForm;
import com.backend.form.RoomCreateForm;
import com.backend.form.RoomFilterForm;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

public interface IRoomService {
    Page<RoomDto> findAll(RoomFilterForm form, int pageNo, int pageSize, String sortBy, String sortDir);
    @Transactional
    RoomDto create(RoomCreateForm form);
    RoomDto findById(Long id);
    RoomDto update(Long id,RoomCreateForm form);
    void deleteById(Long id);
}
