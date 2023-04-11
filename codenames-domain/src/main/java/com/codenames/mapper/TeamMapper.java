package com.codenames.mapper;

import com.codenames.dto.TeamDto;
import com.codenames.dto.UserDto;
import com.codenames.models.forooms.Team;

import java.util.List;

public class TeamMapper {

    public static TeamDto teamToTeamDto(Team team){
        UserDto masterDto = team.getMaster() == null ? null : UserMapper.userToRoomDto(team.getMaster().getUser());
        List<UserDto> playersDtoList = UserMapper.usersToUserDtoList(team.getPlayers());

        return new TeamDto(team.getColor(), team.getScore(), masterDto, playersDtoList, team.getMessages());
    }
}
