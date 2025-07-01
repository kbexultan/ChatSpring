package com.example.demo.controller;

import com.example.demo.dto.ChatRoomDto;
import com.example.demo.model.ChatRoom;
import com.example.demo.services.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chatrooms")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    @PostMapping
    public ResponseEntity<ChatRoomDto> createChatRoom(@RequestBody ChatRoom chatRoom) {//@RequestBody принимает JSON -> обьект java
        try {
            ChatRoomDto createdChatRoom = chatRoomService.createChatRoom(chatRoom);
            return ResponseEntity.ok(createdChatRoom);//если все правильно то возвращает 200 ОК
        } catch (IllegalArgumentException e) {// либо exception возвращает 400
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<ChatRoomDto> getChatRoomById(@PathVariable Long id) {//@PathVariable Long id возьми id из URL
        ChatRoomDto chatRoomDto = chatRoomService.getChatRoomById(id);
        return ResponseEntity.ok(chatRoomDto);
    }

    @GetMapping
    public ResponseEntity<List<ChatRoomDto>> getAllChatRooms() {
        List<ChatRoomDto> chatRooms = chatRoomService.getAllChatRooms();
        return ResponseEntity.ok(chatRooms);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChatRoom(@PathVariable Long id) {
        chatRoomService.deleteChatRoom(id);
        return ResponseEntity.noContent().build();
    }
}
