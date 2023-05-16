import React, {useEffect, useState} from 'react';

import {useParams} from "react-router-dom";

import {IGameRoom, Status} from "../../models";

import {useCodeNamesWsRoomConnect} from "../../hooks";
import {StyledCodeNamesGameFrame} from "./GameRoomStyles";
import {
    StyledCNRunGameFrame, StyledCNStopGameMenu,
    StyledGRAdminControlBlock,
    StyledGrayWhiteBG
} from "../../components";



interface GameRoomParamProps {
    [key: string]: string;

    id: string;
}

const GameRoom = () => {

    const newRoomInfo = (room: IGameRoom) => {
        console.log(room)

        setRoom(room);
    }

    const params = useParams<GameRoomParamProps>()

    const [roomSocket, requests, isConnected] = useCodeNamesWsRoomConnect(Number(params.id), newRoomInfo);
    const [room, setRoom] = useState<IGameRoom>();

    useEffect(() => {
        document.title = "Room " + params.id + " - CodeNamesConfig";
    }, [])

    return (
        <StyledCodeNamesGameFrame>

            <StyledCNRunGameFrame
                room={room}
                hidden={room?.status !== Status.RUN}
            />

            <StyledCNStopGameMenu
                room={room}
                requests={requests}
            />

            <StyledGRAdminControlBlock
                onClickToGamePause={() => {
                    console.log("pause")
                }}
                onClickToGameRestart={() => requests?.restartGame()}
                onClickToGameStop={() => requests?.stopGame()}
                hidden={room?.status !== Status.RUN}
            />
            <StyledGrayWhiteBG/>
        </StyledCodeNamesGameFrame>
    );
};

export default GameRoom;