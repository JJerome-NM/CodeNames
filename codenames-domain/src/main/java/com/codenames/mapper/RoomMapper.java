package com.codenames.mapper;

import com.codenames.models.game.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.codenames.dto.RoomDto;
import com.codenames.models.room.Room;
import org.mapstruct.factory.Mappers;


@Mapper
public interface RoomMapper {

    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    TeamMapper TEAM_MAPPER = Mappers.getMapper(TeamMapper.class);

    PlayerMapper PLAYER_MAPPER = Mappers.getMapper(PlayerMapper.class);

    WordsMapper WORDS_MAPPER = Mappers.getMapper(WordsMapper.class);


    @Mapping(target = "blueTeam", expression = "java(TEAM_MAPPER.teamToTeamDto(room.getBlueTeam()))")
    @Mapping(target = "yellowTeam", expression = "java(TEAM_MAPPER.teamToTeamDto(room.getYellowTeam()))")
    @Mapping(target = "spectators", expression = "java(PLAYER_MAPPER.playerListToUserDtoList(room.getSpectators()))")
    @Mapping(target = "words", expression = "java(WORDS_MAPPER.wordsListToWordsDtoList(room.getWords(), player))")
    @Mapping(target = "wordCount", expression = "java(room.getWords().size())")
    @Mapping(target = "timer", expression = "java(room.getTimer().getTime())")
    RoomDto roomToRoomDto(Room room, Player player);

}
