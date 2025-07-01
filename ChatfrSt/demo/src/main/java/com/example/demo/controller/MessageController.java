package com.example.demo.controller;

import com.example.demo.dto.MessageDto;
import com.example.demo.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public ResponseEntity<MessageDto> sendMessage(@RequestBody MessageDto messageDto) {
        MessageDto createdMessage = messageService.sendMessage(messageDto);
        return ResponseEntity.ok(createdMessage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageDto> getMessageById(@PathVariable Long id) {
        MessageDto messageDto = messageService.getMessageById(id);
        return ResponseEntity.ok(messageDto);
    }

    @GetMapping("/chatroom/{chatRoomId}")
    public ResponseEntity<List<MessageDto>> getAllMessagesByChatRoomId(@PathVariable Long chatRoomId) {
        List<MessageDto> messages = messageService.getAllMessagesByChatRoomId(chatRoomId);
        return ResponseEntity.ok(messages);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }
}
