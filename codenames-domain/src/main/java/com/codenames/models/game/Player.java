package com.codenames.models.game;

import com.codenames.enums.PlayerRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class Player {
    private String wsSessionId;
    private PlayerRole playerRole;
    private final User user;
}
