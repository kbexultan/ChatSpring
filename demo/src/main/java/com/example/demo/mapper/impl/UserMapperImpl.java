package com.example.demo.mapper.impl;

import com.example.demo.dto.UsersDto;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapperImpl implements UserMapper {
    @Override
    public UsersDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UsersDto usersDto = new UsersDto();
        usersDto.setId(user.getId());
        usersDto.setUsername(user.getUsername());
        usersDto.setPassword(user.getPassword());
        return usersDto;
    }
}
