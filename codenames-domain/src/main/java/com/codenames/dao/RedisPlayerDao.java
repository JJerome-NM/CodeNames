package com.codenames.dao;

import com.codenames.redis_entity.HashedPlayer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RedisPlayerDao {

    private static final String HASH_KEY = "authorize_player";

    private RedisTemplate<String, Object> template;

    public void save(HashedPlayer hashedPlayer){
        template.opsForHash().put(HASH_KEY, hashedPlayer.getId(), hashedPlayer);
    }

    public void removeHashedPlayerById(int id){
        template.opsForHash().delete(HASH_KEY, id);
    }

    public HashedPlayer findHashedPlayerById(int id){
        return (HashedPlayer) template.opsForHash().get(HASH_KEY, id);
    }
}
