package com.codenames.mapper;

import com.codenames.dto.UserDto;
import com.codenames.models.forgame.Player;
import com.codenames.models.forgame.User;

import java.util.List;
import java.util.Map;

public class UserMapper {

    public static UserDto userToRoomDto(User user){
        return new UserDto(user.id(), user.nickname());
    }

    public static List<UserDto> usersToUserDtoList(List<User> users){
        return users.stream().map(UserMapper::userToRoomDto).toList();
    }

    public static List<UserDto> usersToUserDtoList(Map<Integer, Player> users){
        return users.values().stream()
                .map(Player::getUser)
                .map(UserMapper::userToRoomDto)
                .toList();
    }

}
