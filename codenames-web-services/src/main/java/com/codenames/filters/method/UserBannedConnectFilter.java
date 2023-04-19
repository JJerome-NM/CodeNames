package com.codenames.filters.method;

import com.jjerome.annotations.FilteringOrder;
import com.jjerome.filters.SocketConnectionFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@FilteringOrder(order = 1)
@Component
public class UserBannedConnectFilter implements SocketConnectionFilter {
    @Override
    public boolean doFilter(WebSocketSession session) {
        // TODO: 18.04.2023 Temporarily empty method it will be finalized
        return true;
    }
}
