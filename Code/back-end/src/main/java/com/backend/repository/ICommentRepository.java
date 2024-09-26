package com.backend.repository;

import com.backend.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentRepository extends JpaRepository<Comment,Long> {
    Page<Comment> findAll(Specification<Comment> spec, Pageable pageable);
}
