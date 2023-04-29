import React, {CSSProperties, useEffect, useRef, useState} from 'react';
import GrayWhiteBG from "../../components/ui/GrayWhiteBG/GrayWhiteBG";
import {IGameRoom} from "../../models/CodeNames/IGameRoom";


import css from './styles/main.module.css'
import CodeNameGameWebSocketService from "../../services/CodeNameGameWebSocketService";
import CNStopGameMenu from "../../components/game/stopped/menu/CNStopGameMenu/CNStopGameMenu";
import CNGameWord from "../../components/game/running/CNGameWord/CNGameWord";
import axios from "axios";
import {useNavigate, useParams} from "react-router-dom";
import useFetching from "../../hooks/useFetching";
import {CodeNamesGameRestService} from "../../services/CodeNamesGameRestService";
import GRAdminControlBlock from "../../components/game/running/settings/GRAdminControlBlock/GRAdminControlBlock";


interface GameRoomParamProps {
    [key: string]: string;

    id: string;
}

const GameRoom = () => {

    const params = useParams<GameRoomParamProps>()
    const navigate = useNavigate();
    const socketService = useRef<CodeNameGameWebSocketService>()

    const [isConnected, setIsConnected] = useState<boolean>(false);
    const [room, setRoom] = useState<IGameRoom>();

    const [fetchConnectToRoom, isLoadingConnectToRoom, errorConnectToRoom] = useFetching(async () => {
        const response = await CodeNamesGameRestService.tryConnectToRoom(Number(params.id));

        if (response.data === -1) {
            navigate("/room/connect");
        } else {
            socketService.current = new CodeNameGameWebSocketService(Number(params.id), newRoomInfo, onConnect)
            setIsConnected(true);
        }
    })

    const newRoomInfo = (room: IGameRoom) => {
        console.log(room)

        setRoom(room);
    }

    const onConnect = () => {
    }

    const calcWordsCountOnLine = (wordCount: number = 20): number => {
        switch (wordCount) {
            case 30:
                return 5;
            case 25:
                return 5;
            case 20:
                return 5;
            case 16:
                return 4;
        }
        return 5;
    }

    useEffect(() => {
        fetchConnectToRoom();
    }, [])

    return (
        <div className={css.CodeNamesGameFrame}>
            <CNStopGameMenu
                room={room}
                service={socketService.current}
            />

            <GRAdminControlBlock/>

            <div
                style={{"--words-line-count": `${calcWordsCountOnLine(room?.wordsCount)}`} as CSSProperties}
                className={css.WordsBlock}
            >
                {room?.words.map(word =>
                    <CNGameWord color={word.color} key={word.id}>{word.text}</CNGameWord>
                )}
            </div>

            <GrayWhiteBG/>
        </div>
    );
};

export default GameRoom;