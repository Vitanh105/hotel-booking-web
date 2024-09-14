package com.backend.service;

import com.backend.dto.CommentDto;
import com.backend.dto.HotelDto;
import com.backend.form.CommentCreateForm;
import com.backend.form.CommentFilterForm;
import com.backend.form.HotelCreateForm;
import com.backend.form.HotelFilterForm;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

public interface ICommentService {
    Page<CommentDto> findAll(CommentFilterForm form, int pageNo, int pageSize, String sortBy, String sortDir);
    @Transactional
    CommentDto create(CommentCreateForm form);
    CommentDto findById(Long id);
    CommentDto update(Long id, CommentCreateForm form);
    void deleteById(Long id);
}
