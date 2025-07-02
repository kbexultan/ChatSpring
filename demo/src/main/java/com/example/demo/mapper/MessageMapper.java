package com.example.demo.mapper;

import com.example.demo.dto.MessageDto;
import com.example.demo.model.Message;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    @Mappings({
            @Mapping(target = "chatRoomId", source = "chatRoom.id"),
            @Mapping(target = "senderId", source = "sender.id")
    })
    MessageDto toDto(Message message);

    @InheritInverseConfiguration
    Message toEntity(MessageDto dto);
}
