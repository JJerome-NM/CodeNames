package com.codenames.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.codenames.dto.RoomDto;
import com.codenames.domain.room.Room;


@Mapper(
        uses = {TeamMapper.class, PlayerMapper.class, WordsMapper.class},
        componentModel = "spring"
)
public interface RoomMapper {

    @Mapping(target = "id", source = "room.id")
    @Mapping(target = "wordCount", expression = "java(room.getWords().size())")
    @Mapping(target = "timer", expression = "java(room.getTimer().getTime())")
    RoomDto roomToRoomDto(Room room, boolean wordsColorHidden);

}
