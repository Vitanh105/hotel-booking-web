package com.backend;

import com.backend.model.*;
import com.backend.model.enums.RoleType;
import com.backend.responsitory.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class TestDataInitializer implements CommandLineRunner {

    private final RoleResponsitory roleResponsitory;
    private final PasswordEncoder passwordEncoder;
    private final AdminResponsitory adminResponsitory;
    private final HotelManagerResponsitory hotelManagerResponsitory;
    private final UserResponsitory userResponsitory;
    private final CustomerResponsitory customerResponsitory;

    @Override
    @Transactional // Đảm bảo phương thức này chạy trong ngữ cảnh giao dịch
    public void run(String... args) throws Exception {
        try {
            if (roleResponsitory.count() == 0 && userResponsitory.count() == 0) {

            Role adminRole = new Role(RoleType.ADMIN);
            Role customerRole = new Role(RoleType.CUSTOMER);
            Role hotelManagerRole = new Role(RoleType.MANAGER);

            roleResponsitory.save(adminRole);
            roleResponsitory.save(customerRole);
            roleResponsitory.save(hotelManagerRole);

            User user1 = User.builder().username("admin@gmail.com").password(passwordEncoder.encode("1")).firstName("Admin").lastName("Admin").role(adminRole).phone("1234567890").build();
            User user2 = User.builder().username("customer@gmail.com").password(passwordEncoder.encode("1")).firstName("Kaya Alp").lastName("Koker").role(customerRole).phone("1234567890").build();
            User user3 = User.builder().username("manager1@gmail.com").password(passwordEncoder.encode("1")).firstName("John").lastName("Doe").role(hotelManagerRole).phone("1234567890").build();
            User user4 = User.builder().username("manager2@gmail.com").password(passwordEncoder.encode("1")).firstName("Max").lastName("Mustermann").role(hotelManagerRole).phone("1234567890").build();

            user1 = userResponsitory.save(user1);
            user2 = userResponsitory.save(user2);
            user3 = userResponsitory.save(user3);
            user4 = userResponsitory.save(user4);

            // Không cần lấy lại các người dùng

            Admin admin1 = Admin.builder().user(user1).build();
            Customer c1 = Customer.builder().user(user2).build();
            HotelManager hm1 = HotelManager.builder().user(user3).build();
            HotelManager hm2 = HotelManager.builder().user(user4).build();

            adminResponsitory.save(admin1);
            customerResponsitory.save(c1);
            hotelManagerResponsitory.save(hm1);
            hotelManagerResponsitory.save(hm2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
