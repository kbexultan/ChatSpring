package com.example.demo.services;

import com.example.demo.dto.UsersDto;
import com.example.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UsersDto createUser(User user);
    UsersDto getUserById(Long id);
    List<UsersDto> getAllUsers();
    void deleteUser(Long id);
    UsersDto findByUsername(String username);
}