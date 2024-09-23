package com.backend.controller;

import com.backend.model.Customer;
import com.backend.model.LoginResponse;
import com.backend.model.Role;
import com.backend.model.User;
import com.backend.model.enums.RoleType;
import com.backend.model.validate.LoginDTO;
import com.backend.model.validate.UserRegistrationDTO;
import com.backend.responsitory.CustomerResponsitory;
import com.backend.responsitory.RoleResponsitory;
import com.backend.responsitory.UserResponsitory;
import com.backend.service.AuthenticationService;
import com.backend.service.TokenBlacklistService;
import com.backend.util.JwtTokenUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private RoleResponsitory roleResponsitory;

    @Autowired
    private UserResponsitory userResponsitory;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired 
    private HttpServletRequest request;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    CustomerResponsitory customerResponsitory;

    @Autowired
    private TokenBlacklistService tokenBlacklistService;



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO, HttpServletResponse response) {
        try {
            String token = authenticationService.authenticate(loginDTO.getUsername(), loginDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = userResponsitory.findByUsername(loginDTO.getUsername());
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
    
            // Lấy vai trò của người dùng
            String role = user.getRole().getRoleType().name();
            String username = (user.getFirstName()+" "+user.getLastName());

            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(60*60);
            cookie.setPath("/");
            response.addCookie(cookie);
            
            return ResponseEntity.ok(new LoginResponse(token,role,username));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser (@RequestBody UserRegistrationDTO userRegistrationDTO){
        // kiểm tra xem username đã tồn tại hay chưa
        if (userResponsitory.existsByUsername(userRegistrationDTO.getUsername())) {
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }
        //đăng ký user mới
        User user = new User();
        user.setUsername(userRegistrationDTO.getUsername());
        user.setFirstName(userRegistrationDTO.getFirstName());
        user.setLastName(userRegistrationDTO.getLastName());
        user.setPhone(userRegistrationDTO.getPhone());
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword())); // mã hòa mật khẩu
        Role roles = roleResponsitory.findByRoleType(RoleType.CUSTOMER);
        user.setRole(roles);
        userResponsitory.save(user);
        Customer customer = new Customer();
        customer.setId(user.getId());
        customer.setUser(user);
        customerResponsitory.save(customer);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response){
        String authToken = request.getHeader("Authorization");
        if (authToken != null && authToken.startsWith("Bearer ")) {
            String token = authToken.substring(7);
            // vô hiệu hóa token
            tokenBlacklistService.blacklistToken(token);

            // Xóa cookie
            Cookie cookie = new Cookie("token", null);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);

            return ResponseEntity.ok("Logout successful");
        }
        return ResponseEntity.badRequest().body("Invalid token");
    }
}
