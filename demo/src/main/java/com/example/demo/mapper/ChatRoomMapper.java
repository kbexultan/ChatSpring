package com.example.demo.mapper;

import com.example.demo.dto.ChatRoomDto;
import com.example.demo.model.ChatRoom;
import com.example.demo.model.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChatRoomMapper {

    @Mapping(target = "userIds", source = "users")
    ChatRoomDto toDto(ChatRoom chatRoom);

    default List<Long> usersToIds(List<User> users) {
        if (users == null) return null;
        return users.stream().map(User::getId).toList();
    }
}