package com.backend.responsitory;

import com.backend.model.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CustomerResponsitory extends JpaRepository<Customer,Long> {

    Optional<Customer> findByUserId(Long userId);

    // truy vấn customer theo username
    @Query("SELECT c FROM Customer c WHERE c.user.username = :username")
    Optional<Customer> findByUsername(@Param("username") String username);

    @Modifying
    @Transactional
    @Query("DELETE FROM Customer c WHERE c.user.id = :user_id ")
    void deleteByUserId(@Param ("user_id")Long user_id);

}
