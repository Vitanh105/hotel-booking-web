package com.backend.responsitory;

import com.backend.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AdminResponsitory extends JpaRepository<Admin,Long> {
    
}
