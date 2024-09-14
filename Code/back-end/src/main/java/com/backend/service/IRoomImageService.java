package com.backend.service;

import com.backend.dto.RoomDto;
import com.backend.dto.RoomImageDto;
import com.backend.form.RoomCreateForm;
import com.backend.form.RoomFilterForm;
import com.backend.form.RoomImageCreateForm;
import com.backend.form.RoomImageFilterForm;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

public interface IRoomImageService {


    Page<RoomImageDto> findAll(RoomImageFilterForm form, int pageNo, int pageSize, String sortBy, String sortDir);
    @Transactional
    RoomImageDto create(RoomImageCreateForm form);
    RoomImageDto findById(Long id);
    RoomImageDto update(Long id,RoomImageCreateForm form);
    void deleteById(Long id);
}
