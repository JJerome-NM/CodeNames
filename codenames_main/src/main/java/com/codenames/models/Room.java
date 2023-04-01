package com.codenames.models;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private final int id;

    private final Status status = Status.STOPPED;

    private final User roomAdmin;

    private final Team blueTeam = new Team();

    private final Team yellowTeam = new Team();

    private final List<User> spectators = new ArrayList<>();

    private final List<Word> words = new ArrayList<>();

    private final Settings settings = new Settings();

    private final Timer timer = new Timer();

    private final List<User> bannedUsers = new ArrayList<>();

    Room(User roomAdmin){
        this.roomAdmin = roomAdmin;
        this.id = 100;
    }

}
