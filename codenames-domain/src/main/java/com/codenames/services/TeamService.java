package com.codenames.services;

import com.codenames.domain.game.Player;
import com.codenames.domain.room.Team;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    public boolean compareTeamPlayers(Team team, List<Player> secondPlayers) {
        if (team.getPlayers().size() != secondPlayers.size()) {
            return false;
        }

        for (Player player : secondPlayers) {
            if (!team.getPlayers().containsKey(player.getUser().id())) {
                return false;
            }
        }
        return true;
    }

    public void addMessage(Team team, String message) {
        team.getMessages().add(message);
    }

    public void addUserToTeam(Team team, Player player) {
        team.getPlayers().put(player.getUser().id(), player);
    }

    public boolean checkUserInTeam(Team team, Player player) {
        return team.getPlayers().containsValue(player);
    }

    public void removeUserFromTeam(Team team, Player player) {
        team.getPlayers().remove(player.getUser().id());
    }

    public boolean checkUserInMaster(Team team, Player player) {
        return team.getMaster() == player;
    }

    public void removeUserFromMaster(Team team, Player player) {
        if (team.getMaster() != null && team.getMaster().equals(player)) {
            team.setMaster(null);
        }
    }

    public void selectMaster(Team team, Player player) {
        if (team.getMaster() == null) {
            team.setMaster(player);
        }
    }

    public boolean masterRoleAvailable(Team team) {
        return team.getMaster() == null;
    }

    public boolean playerRoleAvailable(Team team) {
        return team.getPlayers().size() < 10;
    }
}
