package com.codenames.models.for_game;

import com.codenames.dto.UserDto;
import com.codenames.enums.PlayerRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public record Player(PlayerRole playerRole, User user) {

    public UserDto toUserDto(){
        return this.user.toUserDto();
    }
}
