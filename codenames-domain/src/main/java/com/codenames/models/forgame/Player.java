package com.codenames.models.forgame;

import com.codenames.enums.PlayerRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class Player {
    private PlayerRole playerRole;
    private final User user;
}
