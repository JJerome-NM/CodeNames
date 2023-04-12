package com.codenames.mapper;

import com.codenames.dto.TeamDto;
import com.codenames.dto.UserDto;
import com.codenames.models.forooms.Team;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.factory.Mappers;

import java.util.List;

//@Mapper
public class TeamMapper {

//    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);

//    TeamDto teamToTeamDto(Team team);

    public static TeamDto teamToTeamDto(Team team){
        UserDto masterDto = team.getMaster() == null ? null : UserMapper.userToRoomDto(team.getMaster().getUser());
        List<UserDto> playersDtoList = UserMapper.usersToUserDtoList(team.getPlayers());

        return new TeamDto(team.getColor(), team.getScore(), masterDto, playersDtoList, team.getMessages());
    }
}
