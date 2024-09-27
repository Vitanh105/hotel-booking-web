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

    public User update(Long id, User updateUser) {
        return userRepository.findById(id).map(user -> {
            user.setFullName(updateUser.getFullName());
            user.setAge(updateUser.getAge());
            user.setGender(updateUser.getGender());
            user.setPhoneNumber(updateUser.getPhoneNumber());
            user.setIdentity(updateUser.getIdentity());
            user.setAddress(updateUser.getAddress());
            user.setRole(updateUser.getRole());
            user.setAvatar(updateUser.getAvatar());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with id " + id));
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

    public Optional<User> findByFullName(String fullName) {
        return userRepository.findByFullName(fullName);
    }

    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(user);
    }

    public User changPassword(User updatedUser){
        Long id = updatedUser.getId();
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setPassword(updatedUser.getPassword());
            return userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User not found with id: " + id);
        }
    }
}
