package com.codenames.mapper;

//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;

import com.codenames.dto.RoomDto;
import com.codenames.dto.TeamDto;
import com.codenames.dto.UserDto;
import com.codenames.dto.WordDto;
import com.codenames.models.forgame.Player;
import com.codenames.models.forooms.Room;
//import org.mapstruct.factory.Mappers;

import java.util.List;

//@Mapper
public class RoomMapper {

//    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);
//
//    @Mapping(target = "status", source = "room.status")
//    @Mapping(target = "blueTeam", source = "room.blueTeam")
//    @Mapping(target = "yellowTeam", source = "room.yellowTeam")
//    @Mapping(target = "spectators", source = "room.spectators")
//    @Mapping(target = "words", source = "room.words")
//    @Mapping(target = "wordCount", source = "room.wordCount")
//    @Mapping(target = "timer", source = "room.timer.getTime")
//    RoomDto roomToRoomDto(Room room);

    public static RoomDto roomToRoomDto(Room room, Player player){
        TeamDto blueTeamDto = TeamMapper.teamToTeamDto(room.getBlueTeam());
        TeamDto yellowTeamDto = TeamMapper.teamToTeamDto(room.getYellowTeam());
        List<UserDto> spectatorsDtoList = UserMapper.usersToUserDtoList(room.getSpectators().stream()
                .map(Player::getUser).toList());
        List<WordDto> wordDtoList = WordsMapper.wordsToWordsDtoList(room.getWords(), player);

        int wordsCount = room.getSettings().getWordsSettings().getWordsCount();

        return new RoomDto(room.getStatus(), room.getGameTurn(), blueTeamDto, yellowTeamDto, spectatorsDtoList,
                wordDtoList, wordsCount, room.getTimer().getTime());
    }

}
