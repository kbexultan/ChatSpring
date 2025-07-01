package com.example.demo.mapper;

import com.example.demo.dto.MessageDto;
import com.example.demo.model.ChatRoom;
import com.example.demo.model.Message;
import com.example.demo.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-01T12:54:54+0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Microsoft)"
)
@Component
public class MessageMapperImpl implements MessageMapper {

    @Override
    public MessageDto toDto(Message message) {
        if ( message == null ) {
            return null;
        }

        MessageDto messageDto = new MessageDto();

        messageDto.setChatRoomId( messageChatRoomId( message ) );
        messageDto.setSenderId( messageSenderId( message ) );
        messageDto.setId( message.getId() );
        messageDto.setContent( message.getContent() );
        messageDto.setTimestamp( message.getTimestamp() );

        return messageDto;
    }

    @Override
    public Message toEntity(MessageDto dto) {
        if ( dto == null ) {
            return null;
        }

        Message message = new Message();

        message.setChatRoom( messageDtoToChatRoom( dto ) );
        message.setSender( messageDtoToUser( dto ) );
        message.setId( dto.getId() );
        message.setContent( dto.getContent() );
        message.setTimestamp( dto.getTimestamp() );

        return message;
    }

    private Long messageChatRoomId(Message message) {
        if ( message == null ) {
            return null;
        }
        ChatRoom chatRoom = message.getChatRoom();
        if ( chatRoom == null ) {
            return null;
        }
        Long id = chatRoom.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long messageSenderId(Message message) {
        if ( message == null ) {
            return null;
        }
        User sender = message.getSender();
        if ( sender == null ) {
            return null;
        }
        Long id = sender.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected ChatRoom messageDtoToChatRoom(MessageDto messageDto) {
        if ( messageDto == null ) {
            return null;
        }

        ChatRoom chatRoom = new ChatRoom();

        chatRoom.setId( messageDto.getChatRoomId() );

        return chatRoom;
    }

    protected User messageDtoToUser(MessageDto messageDto) {
        if ( messageDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( messageDto.getSenderId() );

        return user.build();
    }
}
