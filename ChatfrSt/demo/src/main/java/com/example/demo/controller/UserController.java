package com.example.demo.controller;

import com.example.demo.dto.UsersDto;
import com.example.demo.model.User;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;


    @GetMapping("/id/{id}")
    public ResponseEntity<UsersDto> getUserById(@PathVariable Long id) {
        UsersDto userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UsersDto> getUserByUsername(@PathVariable String username) {
        UsersDto user = userService.findByUsername(username);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UsersDto>> getAllUsers() {
        List<UsersDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

}
