package com.codenames.mapper;

import com.codenames.dto.TeamDto;
import com.codenames.domain.room.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        uses = {PlayerMapper.class},
        componentModel = "spring"
)
public interface TeamMapper {

    @Mapping(target = "players", expression = "java(playerMapper.playerListToUserDtoList(team.getPlayersList()))")
    TeamDto teamToTeamDto(Team team);

}
