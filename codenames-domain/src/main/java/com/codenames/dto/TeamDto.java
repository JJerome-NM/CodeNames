package com.codenames.dto;

import com.codenames.enums.Color;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter @Setter
public class TeamDto {
    private final Color color;

    private int score;

    private UserDto master;

    private final List<UserDto> players;

    private final List<String> messages;
}
