package com.codenames.models;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BannedUsers {

    private final List<User> bannedUsers = new ArrayList<>();

    public void banUser(User user){
        this.bannedUsers.add(user);
    }

    public boolean checkUserBlocked(User user){
        return this.bannedUsers.contains(user);
    }

    public void unBanUser(User user){
        this.bannedUsers.remove(user);
    }
}
