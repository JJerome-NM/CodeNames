package com.codenames;

import com.jjerome.annotations.SocketConnectMapping;
import com.jjerome.annotations.SocketController;
import com.jjerome.annotations.SocketMapping;
import com.jjerome.dto.Request;
import com.jjerome.models.MessageSender;
import com.jjerome.models.SocketApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.WebSocketSession;

@SocketController
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    private static final MessageSender MESSAGE_SENDER = SocketApplication.getMessageSender();

    @SocketConnectMapping
    public void conn(WebSocketSession session){
        LOGGER.info(session.getId() + " - connected");
    }

    @SocketMapping(reqPath = "/getCar")
    public void getCar(Request<Integer> request){

        if (request.getRequestBody().intValue() < 3000){
            MESSAGE_SENDER.send(request.getSessionID(), "/newCar", "MERS");
        } else {
            MESSAGE_SENDER.send(request.getSessionID(), "/newCar", "BMW");
        }

    }

}
