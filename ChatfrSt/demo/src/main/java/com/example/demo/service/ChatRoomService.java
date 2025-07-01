package com.example.demo.services;

import com.example.demo.dto.ChatRoomDto;
import com.example.demo.model.ChatRoom;

import java.util.List;

public interface ChatRoomService {
    ChatRoomDto createChatRoom(ChatRoom chatRoom);
    ChatRoomDto getChatRoomById(Long id);
    List<ChatRoomDto> getAllChatRooms();
    void deleteChatRoom(Long id);
}