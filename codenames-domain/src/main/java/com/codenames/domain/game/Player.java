package com.codenames.domain.game;

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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Player sPlayer)) {
            return false;
        } else {
            return sPlayer.getUser().id() == this.getUser().id();
        }
    }
}
