package com.codenames.mapper;

import com.codenames.dto.TeamDto;
import com.codenames.models.room.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {PlayerMapper.class})
public interface TeamMapper {

    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);


    @Mapping(target = "players", expression = "java(playerMapper.playerListToUserDtoList(team.getPlayersList()))")
    TeamDto teamToTeamDto(Team team);

}
