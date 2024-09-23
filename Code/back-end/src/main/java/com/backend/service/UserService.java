package com.backend.service;

import com.backend.model.validate.ResetPasswordDTO;
import com.backend.model.validate.UserDTO;
import com.backend.model.validate.UserRegistrationDTO;
import com.backend.model.User;

import java.util.List;

public interface UserService {

    // thông qua validate user lưu thông tin user xuống csdl
    com.backend.model.User saveUser(UserRegistrationDTO registrationDTO);

    // đăng ký
    User findUserByUsername(String username);

    UserDTO findUserDTOByUsername(String username);

    // lấy người dùng qua ID
    UserDTO findUserById(Long id);

    // lấy tất cả người dùng
    List<UserDTO> findAllUsers();

    //update người dùng
    void updateUser(UserDTO userDTO);


    void updateLoggedById(Long id);

    // xóa người dùng bằng id
    void deleteUserById(Long id);

    // đổi mật khẩu
    User resetPassword(ResetPasswordDTO resetPasswordDTO);
}
