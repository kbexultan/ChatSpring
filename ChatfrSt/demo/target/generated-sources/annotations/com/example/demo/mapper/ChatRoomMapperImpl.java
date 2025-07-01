package com.example.demo.mapper;

import com.example.demo.dto.ChatRoomDto;
import com.example.demo.model.ChatRoom;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-01T12:54:54+0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Microsoft)"
)
@Component
public class ChatRoomMapperImpl implements ChatRoomMapper {

    @Override
    public ChatRoomDto toDto(ChatRoom chatRoom) {
        if ( chatRoom == null ) {
            return null;
        }

        ChatRoomDto chatRoomDto = new ChatRoomDto();

        chatRoomDto.setUserIds( usersToIds( chatRoom.getUsers() ) );
        chatRoomDto.setId( chatRoom.getId() );
        chatRoomDto.setName( chatRoom.getName() );
        chatRoomDto.setGroupChat( chatRoom.isGroupChat() );

        return chatRoomDto;
    }
}
