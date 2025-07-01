package com.example.demo.services.impl;

import com.example.demo.dto.ChatRoomDto;
import com.example.demo.mapper.ChatRoomMapper;
import com.example.demo.model.ChatRoom;
import com.example.demo.model.User;
import com.example.demo.repository.ChatRoomRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.ChatRoomService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {


    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;
    private final ChatRoomMapper chatRoomMapper;

    @Override
    @Transactional
    public ChatRoomDto createChatRoom(ChatRoom chatRoom) {
        if (chatRoomRepository.existsByName(chatRoom.getName())) {
            throw new IllegalArgumentException("Chat room with name " + chatRoom.getName() + " already exists");
        }


        if (chatRoom.getUsers() != null && !chatRoom.getUsers().isEmpty()) {
            List<User> users = chatRoom.getUsers().stream()
                    .map(user -> userRepository.findById(user.getId())
                            .orElseThrow(() -> new RuntimeException("User not found: " + user.getId())))
                    .collect(Collectors.toList());
            chatRoom.setUsers(users);

            // Обновление обратных связей
            users.forEach(user -> user.getChatRooms().add(chatRoom));
        }

        ChatRoom savedChatRoom = chatRoomRepository.save(chatRoom);
        // Логирование для проверки
        System.out.println("Saved ChatRoom: " + savedChatRoom.getName() + ", Users: " + savedChatRoom.getUsers().size());
        savedChatRoom.getUsers().forEach(user -> System.out.println("User ID: " + user.getId()));

        return chatRoomMapper.toDto(savedChatRoom);
    }


    @Override
    public ChatRoomDto getChatRoomById(Long id) {
        ChatRoom chatRoom = chatRoomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chat room not found"));
        return chatRoomMapper.toDto(chatRoom);
    }

    @Override
    @Transactional
    public List<ChatRoomDto> getAllChatRooms() {
        List<ChatRoom> chatRooms = (List<ChatRoom>) chatRoomRepository.findAll();
        ChatRoom cr = chatRoomRepository.findById(1L).orElseThrow();
        chatRooms.forEach(chatRoom -> {
            System.out.println("ChatRoom: " + chatRoom.getName() + ", Users: " + chatRoom.getUsers().size());
            chatRoom.getUsers().forEach(user -> System.out.println("User ID: " + user.getId()));
        });
        return chatRooms.stream()
                .map(chatRoomMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteChatRoom(Long id) {
        chatRoomRepository.deleteById(id);
    }
}





