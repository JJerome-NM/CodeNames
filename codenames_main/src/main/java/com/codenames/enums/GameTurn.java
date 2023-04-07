package com.codenames.enums;

public enum GameTurn {
    BLUE_MASTER, BLUE_PLAYERS, YELLOW_MASTER, YELLOW_PLAYERS;

    public GameTurn nextTurn(){
        return switch (this){
            case BLUE_MASTER -> BLUE_PLAYERS;
            case BLUE_PLAYERS -> YELLOW_MASTER;
            case YELLOW_MASTER -> YELLOW_PLAYERS;
            case YELLOW_PLAYERS -> BLUE_MASTER;
        };
    }
}
