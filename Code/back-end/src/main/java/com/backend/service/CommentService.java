package com.backend.service;

import com.backend.dto.CommentDto;
import com.backend.model.Comment;
import com.backend.form.CommentCreateForm;
import com.backend.form.CommentFilterForm;
import com.backend.repository.ICommentRepository;
import com.backend.specification.CommentSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service

public class CommentService implements ICommentService{

    private  final ICommentRepository repository;
    private  final ModelMapper modelMapper;
@Autowired
    public CommentService(ICommentRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<CommentDto> findAll(CommentFilterForm form, int pageNo, int pageSize, String sortBy, String sortDir) {
        Specification<Comment> spec = CommentSpecification.buildSpecCmt(form);
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Comment> commentPage = repository.findAll(spec,pageable);
        return commentPage.map(comment -> modelMapper.map(comment, CommentDto.class));
    }

    @Override
    public CommentDto create(CommentCreateForm form) {
        var comment =modelMapper.map(form, Comment.class);
        var saveComment=repository.save(comment);
        return  modelMapper.map(saveComment, CommentDto.class);
    }

    @Override
    public CommentDto findById(Long id) {
        return repository.findById(id)
                .map(comment -> modelMapper.map(comment, CommentDto.class))
                .orElse(null);
    }

    @Override
    public CommentDto update(Long id, CommentCreateForm form) {
        var comment =repository.findById(id).orElse(null);
        modelMapper.map(form,comment);
        var saverComment=repository.save(comment);
        return  modelMapper.map(saverComment, CommentDto.class);
    }

    @Override
    public void deleteById(Long id) {
    repository.deleteById(id);

    }
}
