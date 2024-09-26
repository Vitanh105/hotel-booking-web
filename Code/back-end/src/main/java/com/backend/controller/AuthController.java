package com.backend.controller;

import com.backend.jwt.JwtTokenStore;
import com.backend.model.User;
import com.backend.request.LoginRequest;
import com.backend.response.ResponseObject;
import com.backend.security.PasswordEncoder;
import com.backend.service.AuthService;
import com.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody User user) {

        boolean checkExist =
                userService.checkExistEmailOrPhoneNumber(user.getEmail(), user.getPhoneNumber());

        if(checkExist)
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, "null", "Email or Phone already exists"));

        user.setPassword(PasswordEncoder.getInstance().encodePassword(user.getPassword()));
        User response = userService.create(user);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, response, "success"));

    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody LoginRequest loginRequest) {

        Optional<User> user = userService.findByEmail((loginRequest.getEmail()));
        if (user.isEmpty() || !PasswordEncoder.getInstance().matches(loginRequest.getPassword(), user.get().getPassword())) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(201, user, "Email or Password incorrect"));
        }

        String token = authService.loginWithEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
        JwtTokenStore.getInstance().storeToken(loginRequest.getEmail(), token);
        return ResponseEntity.status(HttpStatus.OK).body
                (new ResponseObject(200,user,token));
    }
}
