package com.backend.responsitory;

import com.backend.model.Role;
import com.backend.model.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleResponsitory extends JpaRepository<Role,Long> {
    Role findByRoleType(RoleType roleType);
}
