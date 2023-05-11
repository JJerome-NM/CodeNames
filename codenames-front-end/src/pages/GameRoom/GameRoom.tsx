import React, {useEffect, useState} from 'react';

import {useParams} from "react-router-dom";

import {IGameRoom} from "../../models/CodeNames/IGameRoom";
import {Status} from "../../models/CodeNames/Status";

import {useCodeNamesWsRoomConnect} from "../../hooks/useCodeNamesWsRoomConnect";
import {Flip, ToastContainer} from "react-toastify";
import {StyledCodeNamesGameFrame} from "./GameRoomStyles";
import {StyledCNRunGameFrame} from "../../components/game/running/CNRunGameFrame/StyledCNRunGameFrame";
import {StyledCNStopGameMenu} from "../../components/game/stopped/menu/CNStopGameMenu/StyledCNStopGameMenu";
import {
    StyledGRAdminControlBlock
} from "../../components/game/running/settings/GRAdminControlBlock/StyledGRAdminControlBlock";
import {StyledGrayWhiteBG} from "../../components/ui/GrayWhiteBG/StyledGrayWhiteBG";


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

            <StyledGrayWhiteBG/>
        </StyledCodeNamesGameFrame>
    );
};

export default GameRoom;