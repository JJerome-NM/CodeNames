package com.codenames.mapper;

import com.codenames.dto.UserDto;
import com.codenames.models.game.Player;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PlayerMapper {

    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);

    @Mapping(target = "id", expression = "java(player.getUser().id())")
    @Mapping(target = "nickname", expression = "java(player.getUser().nickname())")
    UserDto playerToUserDto(Player player);

    @IterableMapping(elementTargetType = UserDto.class)
    List<UserDto> playerListToUserDtoList(List<Player> players);
}
