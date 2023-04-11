package com.codenames.dto;

import com.codenames.enums.GameStatus;
import com.codenames.enums.GameTurn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter @Setter
public class RoomDto {
    private GameStatus status;

    private GameTurn gameTurn;

    private TeamDto blueTeam;

    private TeamDto yellowTeam;

    private List<UserDto> spectators;

    private List<WordDto> words;

    private int wordCount;

    private int timer;
}
