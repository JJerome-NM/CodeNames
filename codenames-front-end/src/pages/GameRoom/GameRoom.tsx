import React, {useEffect, useRef, useState} from 'react';

import {useNavigate, useParams} from "react-router-dom";

import CodeNamesWebSocketService from "../../services/CodeNamesWebSocketService";
import {IGameRoom} from "../../models/CodeNames/IGameRoom";
import {Status} from "../../models/CodeNames/Status";

import CNRunGameFrame from "../../components/game/running/CNRunGameFrame/CNRunGameFrame";
import CNStopGameMenu from "../../components/game/stopped/menu/CNStopGameMenu/CNStopGameMenu";
import GRAdminControlBlock from "../../components/game/running/settings/GRAdminControlBlock/GRAdminControlBlock";
import GrayWhiteBG from "../../components/ui/GrayWhiteBG/GrayWhiteBG";

import css from './styles/main.module.css'
import {useCodeNamesRestService} from "../../hooks/useCodeNamesRestService";
import {useCodeNamesWsRoomConnect} from "../../hooks/useCodeNamesWsRoomConnect";
import {Flip, ToastContainer} from "react-toastify";

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
    const navigate = useNavigate();

    const [roomSocket, requests, isConnected] = useCodeNamesWsRoomConnect(Number(params.id), newRoomInfo);
    const [room, setRoom] = useState<IGameRoom>();

    useEffect(() => {
        document.title = "Room " + params.id + " - CodeNamesConfig";
    }, [])

    return (
        <div className={css.CodeNamesGameFrame}>

            <CNRunGameFrame
                room={room}
            />


            <CNStopGameMenu
                room={room}
                requests={requests}
            />

            <GRAdminControlBlock
                onClickToGamePause={() => {
                    console.log("pause")
                }}
                onClickToGameRestart={() => requests?.restartGame()}
                onClickToGameStop={() => requests?.stopGame()}
                hidden={room?.status !== Status.RUN}
            />

            <ToastContainer
                position="bottom-right"
                transition={Flip}
                autoClose={5000}
                hideProgressBar={false}
                newestOnTop={false}
                closeOnClick={false}
                rtl={false}
                pauseOnFocusLoss
                draggable
                pauseOnHover
                theme="dark"
            />

            <GrayWhiteBG/>
        </div>
    );
};

export default GameRoom;