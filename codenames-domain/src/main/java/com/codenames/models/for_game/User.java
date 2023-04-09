package com.codenames.models.for_game;

import com.codenames.dto.UserDto;

public record User(int id, String ip, String nickname) {

    @Deprecated
    public UserDto toUserDto() {
        return new UserDto(this.id, this.nickname);
    }
}
