package com.example.demo.mapper;

import com.example.demo.dto.UsersDto;
import com.example.demo.model.User;

public interface UserMapper {
    UsersDto toDto(User user);
}