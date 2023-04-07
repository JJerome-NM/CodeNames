package com.codenames.models;

import com.codenames.dto.UserDto;

public record User(int id, String ip, String nickname) {

    public UserDto toUserDto() {
        return new UserDto(this.id, this.nickname);
    }
}
