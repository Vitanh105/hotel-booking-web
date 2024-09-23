package com.backend.responsitory;

import com.backend.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AddressResponsitory extends JpaRepository<Address,Long> {

    
}