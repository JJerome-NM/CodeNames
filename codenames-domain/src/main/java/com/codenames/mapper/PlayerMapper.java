package com.codenames.mapper;

import com.codenames.dto.UserDto;
import com.codenames.models.game.Player;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PlayerMapper {

    @Mapping(target = "id", expression = "java(player.getUser().id())")
    @Mapping(target = "nickname", expression = "java(player.getUser().nickname())")
    public abstract UserDto playerToUserDto(Player player);

    @IterableMapping(elementTargetType = UserDto.class)
    public abstract List<UserDto> playerListToUserDtoList(List<Player> players);
}
