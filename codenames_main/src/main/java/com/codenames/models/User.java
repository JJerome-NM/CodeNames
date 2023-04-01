package com.codenames.models;

public class User {

    private final int id;

    private final int ip;

    private final String nickname;


    User(int id, int ip, String nickname){
        this.id = id;
        this.ip = ip;
        this.nickname = nickname;
    }

}
