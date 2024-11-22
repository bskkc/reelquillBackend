package com.example.reelquill.controller;

import com.example.reelquill.dto.LoginRequestDTO;
import com.example.reelquill.dto.LoginResponseDTO;
import com.example.reelquill.model.User;
import com.example.reelquill.util.JwtUtil;
import com.example.reelquill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        return userService.getUserById(id)
                .map(user -> ResponseEntity.ok(user))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        User user = userService.validateUser(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());

        if (user != null) {
            String token = jwtUtil.createToken(loginRequestDTO.getEmail());
            LoginResponseDTO response = new LoginResponseDTO(token, "Giriş başarılı", user);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(401).body("Unauthorized");
    }

    @PostMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User updatedUser) {
        try {
            User user = userService.updateUser(updatedUser);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
