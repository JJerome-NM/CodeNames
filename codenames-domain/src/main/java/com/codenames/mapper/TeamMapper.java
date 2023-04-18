package com.codenames.mapper;

import com.codenames.dto.TeamDto;
import com.codenames.models.room.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TeamMapper {

    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);

    PlayerMapper PLAYER_MAPPER = Mappers.getMapper(PlayerMapper.class);


    @Mapping(target = "master", expression = "java(PLAYER_MAPPER.playerToUserDto(team.getMaster()))")
    @Mapping(target = "players", expression = "java(PLAYER_MAPPER.playerListToUserDtoList(team.getPlayersList()))")
    TeamDto teamToTeamDto(Team team);

}
