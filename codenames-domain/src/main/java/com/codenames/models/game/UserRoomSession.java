package com.codenames.models.game;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
@Data
public class UserRoomSession {

    private int roomID;

    private Player player;
}
