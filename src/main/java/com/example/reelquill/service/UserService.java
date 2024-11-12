package com.example.reelquill.service;

import com.example.reelquill.model.User;
import com.example.reelquill.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        user.setCreationDate(LocalDateTime.now());
        user.setUpdateDate(LocalDateTime.now());
        return userRepository.save(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public User validateUser(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return user.getPassword().equals(password) ? user : null;
        }
        return null;
    }

    public User updateUser(User updatedUser) {
        int id = updatedUser.getId(); // ID'yi objeden alıyoruz
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setUsername(updatedUser.getUsername());
                    existingUser.setEmail(updatedUser.getEmail());
                    existingUser.setPassword(updatedUser.getPassword());
                    existingUser.setUpdateDate(LocalDateTime.now());
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
