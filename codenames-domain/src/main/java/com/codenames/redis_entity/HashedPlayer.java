package com.codenames.redis_entity;

import com.codenames.enums.PlayerRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class HashedPlayer implements Serializable {
    @Id
    private int id;

    private String wsSessionId;

    private PlayerRole playerRole;

    private HashedUser user;
}
