package com.codenames.domain.room;

import com.codenames.enums.Color;
import com.codenames.domain.game.Player;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Getter @Setter
public class Team {

    private final Color color;

    private int score;

    private Player master;

    private final Map<Integer, Player> players = new HashMap<>();

    private final List<String> messages = new ArrayList<>();

    public List<Player> getPlayersList(){
        return players.values().stream().toList();
    }
}
