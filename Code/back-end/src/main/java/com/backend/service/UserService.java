package com.backend.service;

import com.backend.exception.ResourceNotFoundException;
import com.backend.model.User;
import com.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User create(User newUser) {
        if (checkExistEmailOrPhoneNumber(newUser.getEmail(), newUser.getPhoneNumber())) {
            throw new IllegalArgumentException("User with given email or phone number already exists.");
        }

        return userRepository.save(newUser);
    }

    public User update(User updateUser) {

        Optional<User> optionalUser = userRepository.findById(updateUser.getId());

        if (optionalUser.isEmpty()) return null;

        return userRepository.save(updateUser);
    }


    public boolean checkExistEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) return true;

        return false;
    }

    public boolean checkExistEmailOrPhoneNumber(String email, String phoneNumber) {

        Optional<User> optionalUserEmail = userRepository.findByEmail(email);

        Optional<User> optionalUserPhone = userRepository.findByPhoneNumber(phoneNumber);

        if (optionalUserEmail.isPresent()) return true;

        if (optionalUserPhone.isPresent()) return true;

        return false;

    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }


    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(user);
    }
}
