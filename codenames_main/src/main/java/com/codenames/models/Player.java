package com.codenames.models;

import com.codenames.dto.UserDto;
import com.codenames.enums.PlayerRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class Player {
    private PlayerRole playerRole;
    private final User user;

    public UserDto toUserDto(){
        return this.user.toUserDto();
    }
}
