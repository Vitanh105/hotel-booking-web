package com.backend.responsitory;

import com.backend.model.HotelManager;
import com.backend.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface HotelManagerResponsitory extends JpaRepository<HotelManager,Long> {
    Optional<HotelManager> findByUser(User user);
        // Tìm HotelManager theo User ID
    HotelManager findByUserId(Long userId);

    // Xóa HotelManager theo User ID
    @Modifying
    @Transactional
    @Query("DELETE  FROM HotelManager m WHERE m.user.id = :user_id")
     void deleteByUserId(@Param ("user_id") Long userId);
}
