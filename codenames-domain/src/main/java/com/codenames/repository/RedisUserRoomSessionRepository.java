package com.codenames.repository;

import com.codenames.properties.CodeNamesProperties;
import com.codenames.redis_entity.HashedUserRoomSession;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RedisUserRoomSessionRepository {

    private final String hashKey;

    private final RedisTemplate<String, Object> template;

    RedisUserRoomSessionRepository(CodeNamesProperties codeNamesProperties,
                                   RedisTemplate<String, Object> template){
        this.template = template;
        this.hashKey = codeNamesProperties.getRedis().getUserRoomSessionsHashKey();
    }

    public void save(HashedUserRoomSession hashedPlayer){
        template.opsForHash().put(hashKey, hashedPlayer.getPlayer().getWsSessionId(), hashedPlayer);
    }

    public void removeByWsSessionID(String wsSessionId){
        template.opsForHash().delete(hashKey, wsSessionId);
    }

    public Optional<HashedUserRoomSession> findByWsSessionID(String wsSessionId){
        return Optional.of((HashedUserRoomSession) template.opsForHash().get(hashKey, wsSessionId));
    }
}
