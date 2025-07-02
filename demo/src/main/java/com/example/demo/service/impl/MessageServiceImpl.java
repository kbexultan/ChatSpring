package com.example.demo.services.impl;

import com.example.demo.dto.MessageDto;
import com.example.demo.mapper.MessageMapper;
import com.example.demo.model.ChatRoom;
import com.example.demo.model.Message;
import com.example.demo.model.User;
import com.example.demo.repository.ChatRoomRepository;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    @Override
    public MessageDto sendMessage(MessageDto messageDto) {
        // Находим чат
        ChatRoom chatRoom = chatRoomRepository.findById(messageDto.getChatRoomId())
                .orElseThrow(() -> new RuntimeException("Chat room not found"));

        // Находим пользователя-отправителя
        User sender = userRepository.findById(messageDto.getSenderId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Проверяем, состоит ли отправитель в этой чат-комнате
        if (!chatRoom.getUsers().contains(sender)) {
            throw new RuntimeException("User does not belong to the chat room");
        }

        // Мапим DTO → Entity
        Message message = messageMapper.toEntity(messageDto);
        message.setChatRoom(chatRoom);
        message.setSender(sender);
        message.setTimestamp(LocalDateTime.now());

        // Сохраняем и возвращаем DTO
        Message saved = messageRepository.save(message);
        return messageMapper.toDto(saved);
    }

    @Override
    public MessageDto getMessageById(Long id) {
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found"));
        return messageMapper.toDto(message);
    }

    @Override
    public List<MessageDto> getAllMessagesByChatRoomId(Long chatRoomId) {
        return messageRepository.findAllByChatRoomId(chatRoomId).stream()
                .map(messageMapper::toDto)
                .toList();
    }

    @Override
    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }
}
