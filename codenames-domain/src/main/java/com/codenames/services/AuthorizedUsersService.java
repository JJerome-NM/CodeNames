package com.codenames.services;

import com.codenames.dao.RedisUserRoomSessionDao;
import com.codenames.exception.UserRoomSessionNotFoundExeption;
import com.codenames.mapper.UserRoomSessionMapper;
import com.codenames.domain.game.Player;
import com.codenames.domain.game.User;
import com.codenames.domain.game.UserRoomSession;
import com.codenames.redis_entity.HashedUserRoomSession;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@Data
@RequiredArgsConstructor
public class AuthorizedUsersService {

    private final RedisUserRoomSessionDao redisPlayerDao;

    private final UserRoomSessionMapper userRoomSessionMapper;

    public UserRoomSession getUserRoomSession(String sessionID){
        return userRoomSessionMapper.hashedUserRoomSessionToUserRoomSession(redisPlayerDao.findByWsSessionID(sessionID)
                .orElseThrow(() -> new UserRoomSessionNotFoundExeption(sessionID + " not found")));
    }

    public boolean checkUserAuthorized(String sessionID){
        return redisPlayerDao.findByWsSessionID(sessionID).isPresent();
    }

    public boolean checkUserAuthorized(Player player){
        return redisPlayerDao.findByWsSessionID(player.getWsSessionId()).isPresent();
    }

    public void addUserRoomSession(String sessionID, int roomID, User user){
        UserRoomSession session = new UserRoomSession(roomID, new Player(sessionID, null, user));

        redisPlayerDao.save(userRoomSessionMapper.userRoomSessionToHashedUserRoomSession(session));
    }

    public void removeUserRoomSession(Player player){
        redisPlayerDao.removeByWsSessionID(player.getWsSessionId());

        player.setWsSessionId(null);
    }

    public void setNewRoomID(String sessionID, int newRoomID){
        HashedUserRoomSession session = redisPlayerDao.findByWsSessionID(sessionID)
                .orElseThrow(() -> new UserRoomSessionNotFoundExeption(sessionID + " not found"));

        session.setRoomID(newRoomID);

        redisPlayerDao.save(session);
    }
}
