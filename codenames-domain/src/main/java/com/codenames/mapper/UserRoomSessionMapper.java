package com.codenames.mapper;

import com.codenames.domain.game.UserRoomSession;
import com.codenames.redis_entity.HashedUserRoomSession;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRoomSessionMapper {

    UserRoomSession hashedUserRoomSessionToUserRoomSession(HashedUserRoomSession hashedUserRoomSession);

    HashedUserRoomSession userRoomSessionToHashedUserRoomSession(UserRoomSession userRoomSession);

}
