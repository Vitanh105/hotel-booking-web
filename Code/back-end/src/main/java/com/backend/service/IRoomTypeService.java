package com.backend.service;

import com.backend.dto.RoomImageDto;
import com.backend.dto.RoomTypeDto;
import com.backend.form.RoomImageCreateForm;
import com.backend.form.RoomImageFilterForm;
import com.backend.form.RoomTypeCreateForm;
import com.backend.form.RoomTypeFilterForm;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

public interface IRoomTypeService {
    Page<RoomTypeDto> findAll(RoomTypeFilterForm form, int pageNo, int pageSize, String sortBy, String sortDir);
    @Transactional
    RoomTypeDto create(RoomTypeCreateForm form);
    RoomTypeDto findById(Long id);
    RoomTypeDto update(Long id,RoomTypeCreateForm form);
    void deleteById(Long id);
}
