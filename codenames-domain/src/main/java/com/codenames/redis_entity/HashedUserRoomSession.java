package com.codenames.redis_entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("user_room_sessions")
public class HashedUserRoomSession implements Serializable {

    private int roomID;

    private HashedPlayer player;
}
