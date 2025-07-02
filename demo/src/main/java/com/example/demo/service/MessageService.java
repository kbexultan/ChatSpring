package com.example.demo.services;

import com.example.demo.dto.MessageDto;

import java.util.List;

public interface MessageService {
    MessageDto sendMessage(MessageDto messageDto);
    MessageDto getMessageById(Long id);
    List<MessageDto> getAllMessagesByChatRoomId(Long chatRoomId);
    void deleteMessage(Long id);
}
