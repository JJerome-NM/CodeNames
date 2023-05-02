import React, {useEffect, useRef, useState} from 'react';

import {useNavigate, useParams} from "react-router-dom";

import CodeNamesWebSocketService from "../../services/CodeNamesWebSocketService";
import {IGameRoom} from "../../models/CodeNames/IGameRoom";
import useFetching from "../../hooks/useFetching";
import {CodeNamesRestService} from "../../services/CodeNamesRestService";
import {Status} from "../../models/CodeNames/Status";

import CNRunGameFrame from "../../components/game/running/CNRunGameFrame/CNRunGameFrame";
import CNStopGameMenu from "../../components/game/stopped/menu/CNStopGameMenu/CNStopGameMenu";
import GRAdminControlBlock from "../../components/game/running/settings/GRAdminControlBlock/GRAdminControlBlock";
import GrayWhiteBG from "../../components/ui/GrayWhiteBG/GrayWhiteBG";

import css from './styles/main.module.css'

interface GameRoomParamProps {
    [key: string]: string;

    id: string;
}

const GameRoom = () => {

    const params = useParams<GameRoomParamProps>()
    const navigate = useNavigate();
    const socketService = useRef<CodeNamesWebSocketService>()

    const [isConnected, setIsConnected] = useState<boolean>(false);
    const [room, setRoom] = useState<IGameRoom>();

    const [fetchConnectToRoom, isLoadingConnectToRoom, errorConnectToRoom] = useFetching(async () => {
        const response = await CodeNamesRestService.tryConnectToRoom(Number(params.id));

        if (response.data === -1) {
            navigate("/room/connect");
        } else {
            socketService.current = new CodeNamesWebSocketService(Number(params.id), newRoomInfo, onConnect, onClose)
            setIsConnected(true);
        }
    })

    const newRoomInfo = (room: IGameRoom) => {
        console.log(room)

        setRoom(room);
    }

    const onConnect = () => {
    }

    const onClose = () => {
        navigate("/room/connect");
    }

    useEffect(() => {
        fetchConnectToRoom();
        document.title = "Room " + params.id + " - CodeNamesConfig";
    }, [])

    return (
        <div className={css.CodeNamesGameFrame}>

            <CNRunGameFrame
                room={room}
            />


            <CNStopGameMenu
                room={room}
                service={socketService.current}
            />

            <GRAdminControlBlock
                onClickToGamePause={() => {
                    console.log("pause")
                }}
                onClickToGameRestart={() => socketService.current?.restartGame()}
                onClickToGameStop={() => socketService.current?.stopGame()}
                hidden={room?.status !== Status.RUN}
            />

            <GrayWhiteBG/>
        </div>
    );
};

export default GameRoom;