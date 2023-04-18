package com.codenames.mapper;

import com.codenames.models.game.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.codenames.dto.RoomDto;
import com.codenames.models.room.Room;


@Mapper(uses = {TeamMapper.class, PlayerMapper.class, WordsMapper.class})
public interface RoomMapper {

    @Mapping(target = "wordCount", expression = "java(room.getWords().size())")
    @Mapping(target = "timer", expression = "java(room.getTimer().getTime())")
    RoomDto roomToRoomDto(Room room, Player player);

}
