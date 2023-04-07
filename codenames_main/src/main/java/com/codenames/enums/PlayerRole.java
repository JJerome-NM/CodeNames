package com.codenames.enums;


import lombok.ToString;

@ToString
public enum PlayerRole {
    BLUE_PLAYER, YELLOW_PLAYER, BLUE_MASTER, YELLOW_MASTER, SPECTATOR;

    public static PlayerRole convertToPlayerRole(String textValue){
        return switch (textValue){
            case "BLUE_PLAYER" -> BLUE_PLAYER;
            case "YELLOW_PLAYER" -> YELLOW_PLAYER;
            case "BLUE_MASTER" -> BLUE_MASTER;
            case "YELLOW_MASTER" -> YELLOW_MASTER;
            default -> SPECTATOR;
        };
    }
}
