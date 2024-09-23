package com.backend.service.impl;

import com.backend.model.Customer;
import com.backend.model.HotelManager;
import com.backend.model.Role;
import com.backend.model.User;
import com.backend.model.enums.RoleType;
import com.backend.model.validate.ResetPasswordDTO;
import com.backend.model.validate.UserDTO;
import com.backend.model.validate.UserRegistrationDTO;
import com.backend.responsitory.CustomerResponsitory;
import com.backend.responsitory.HotelManagerResponsitory;
import com.backend.responsitory.RoleResponsitory;
import com.backend.responsitory.UserResponsitory;
import com.backend.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService  {

    private UserResponsitory userResponsitory;
    private RoleResponsitory roleResponsitory;
    private CustomerResponsitory customerResponsitory;
    private HotelManagerResponsitory hotelManagerResponsitory;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(
        UserResponsitory userResponsitory,
        RoleResponsitory roleResponsitory,
        CustomerResponsitory customerResponsitory,
        HotelManagerResponsitory hotelManagerResponsitory,
        PasswordEncoder passwordEncoder){
            this.userResponsitory=userResponsitory;
            this.roleResponsitory=roleResponsitory;
            this.customerResponsitory=customerResponsitory;
            this.hotelManagerResponsitory=hotelManagerResponsitory;
            this.passwordEncoder=passwordEncoder;
        }

    @Override
    @Transactional  // để nếu có exception thì sẽ rollback lại bước trước đó và ko lưu dữ liệu xuống database
    public User saveUser(UserRegistrationDTO registrationDTO) {
       Optional<User> existingUser = Optional.ofNullable(userResponsitory.findByUsername(registrationDTO.getUsername()));

       // kiem tra xem ten nguoi dung da dc dang ky hay chua
       if (existingUser.isPresent()) {
        throw new UsernameNotFoundException("This username is already registered!");
       }
       User user = mapRegistrationDtoToUser(registrationDTO);
       if (RoleType.CUSTOMER.equals(registrationDTO.getRoleType())) {
            Customer customer = Customer.builder().user(user).build();
            customerResponsitory.save(customer);
       }else if (RoleType.MANAGER.equals(registrationDTO.getRoleType())) {
        HotelManager hotelManager = HotelManager.builder().user(user).build();
        hotelManagerResponsitory.save(hotelManager);
       }

       User saveUser= userResponsitory.save(user);
       return saveUser;
    }

    // gom RegistrasionDTO với User
    private User mapRegistrationDtoToUser(UserRegistrationDTO registrationDTO) {
        Role userRole = roleResponsitory.findByRoleType(registrationDTO.getRoleType());
        return User.builder()
                .username(registrationDTO.getUsername().trim())
                .password(passwordEncoder.encode(registrationDTO.getPassword()))
                .firstName(formatText(registrationDTO.getFirstName()))
                .lastName(formatText(registrationDTO.getLastName()))
                .role(userRole)
                .build();
    }
    private String formatText(String text){
        return org.springframework.util.StringUtils.capitalize(text.trim());
    }
    @Override
    public User findUserByUsername(String username) {
        return userResponsitory.findByUsername(username);
    }

    // nối User với UserDto
    private UserDTO mapUserToUserDto(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .phone(user.getPhone())
                .build();
    }

    @Override
    public UserDTO findUserDTOByUsername(String username) {
        Optional<User> userOptional = Optional.ofNullable(userResponsitory.findByUsername(username));
        User user = userOptional.orElseThrow(()-> new UsernameNotFoundException("Username not found"));
        return mapUserToUserDto(user);
    }


    @Override
    public UserDTO findUserById(Long id) {
        User user = userResponsitory.findById(id).orElseThrow(()-> new EntityNotFoundException("User not found"));
        return mapUserToUserDto(user);
    }

    @Override
    public List<UserDTO> findAllUsers() {
        List<User> users = userResponsitory.findAll(); // lấy toàn bộ user
        List<UserDTO> userDTOs = new ArrayList<>(); // tạo 1 arraylist 
        for (User user : users) {                     // truyền dữ liệu từ user vào userDto
            UserDTO userDTO = mapUserToUserDto(user);
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    @Override
    @Transactional
    public void updateUser(UserDTO userDTO) {
        User user = userResponsitory.findById(userDTO.getId()).orElseThrow(()-> new IllegalArgumentException("User not found"));
        
        setFormattedDataToUser(user, userDTO);
        userResponsitory.flush();
    }

    @Override
    // đổi 
    public void updateLoggedById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateLoggedById'");
    }

    @Override
    public void deleteUserById(Long id) {
        userResponsitory.deleteById(id);
    }

    @Override
    public User resetPassword(ResetPasswordDTO resetPasswordDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = auth.getName();

        User user = userResponsitory.findByUsername(loggedInUsername);
        
        if (user== null) {
            throw new UsernameNotFoundException("User not found");
        }
        // phần nhập lại mật khẩu để xác thực
        if (!passwordEncoder.matches(resetPasswordDTO.getOldPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Old Password is incorrect");
        }
        user.setPassword(passwordEncoder.encode(resetPasswordDTO.getConfirmNewPassword())); // gán password mới 
        return userResponsitory.save(user);
    }
    
    private void setFormattedDataToUser(User user, UserDTO userDTO) {
        user.setUsername(userDTO.getUsername());
        user.setFirstName(formatText(userDTO.getFirstName()));
        user.setLastName(formatText(userDTO.getLastName()));
        user.setRole(userDTO.getRole());
        user.setPhone(userDTO.getPhone());
    }
    
}
