package com.backend.responsitory;

import com.backend.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ServiceResponsitory extends JpaRepository<Service,Long> {
    
}
