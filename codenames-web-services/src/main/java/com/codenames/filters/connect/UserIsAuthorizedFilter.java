package com.codenames.filters.connect;

import com.codenames.dao.RedisPlayerDao;
import com.codenames.entity.UserEntity;
import com.codenames.exception.UserNotFoundException;
import com.codenames.mapper.UserMapper;
import com.codenames.models.game.AuthorizedUsers;
import com.codenames.provider.UserAuthProvider;
import com.jjerome.filters.SocketConnectionFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class UserIsAuthorizedFilter implements SocketConnectionFilter {

    private final UserAuthProvider userAuthProvider;

    private final AuthorizedUsers authorizedUsers;

    private final UserMapper userMapper;

    @Override
    public boolean doFilter(WebSocketSession session) {

        MultiValueMap<String, String> params = UriComponentsBuilder.fromUri(session.getUri()).build().getQueryParams();

        UserEntity userEntity = userAuthProvider.verifyToken(params.getFirst("auth_token"))
                .orElseThrow(() -> new UserNotFoundException("User not found", HttpStatus.NOT_FOUND));

        authorizedUsers.addUserRoomSession(session.getId(), -1, userMapper.userEntityToUser(userEntity));

        return true;
    }
}
