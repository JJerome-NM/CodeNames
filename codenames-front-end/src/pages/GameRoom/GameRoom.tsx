import React, {CSSProperties, useEffect, useRef, useState} from 'react';
import GrayWhiteBG from "../../components/ui/GrayWhiteBG/GrayWhiteBG";
import {IGameRoom} from "../../models/IGameRoom";


import css from './styles/main.module.css'
import CodeNameGameService from "../../services/CodeNameGameService";
import CNStopGameMenu from "../../components/game/stopped/menu/CNStopGameMenu/CNStopGameMenu";
import CNGameWord from "../../components/game/running/CNGameWord/CNGameWord";


const GameRoom = () => {

    const socketService = useRef<CodeNameGameService>()
    const [room, setRoom] = useState<IGameRoom>()

    const newRoomInfo = (room: IGameRoom) => {
        console.log(room)

        setRoom(room);
    }

    const onConnect = () => {
        socketService?.current?.createRoom();
    }

    const calcWordsCountOnLine = (wordCount: number = 20): number => {
        switch (wordCount){
            case 30: return 5;
            case 25: return 5;
            case 20: return 5;
            case 16: return 4;
        }
        return 5;
    }

    useEffect(() => {
        socketService.current = new CodeNameGameService(newRoomInfo, onConnect)
    }, [])

    return (
        <div className={css.CodeNamesGameFrame}>
            <CNStopGameMenu
                room={room}
                service={socketService.current}
            />

            <div
                style={{"--words-line-count": `${calcWordsCountOnLine(room?.wordsCount)}`} as CSSProperties}
                className={css.WordsBlock}
            >
                {room?.words.map(word=>
                    <CNGameWord color={word.color} key={word.id}>{word.text}</CNGameWord>
                )}
            </div>

            <GrayWhiteBG/>
        </div>
    );
};

export default GameRoom;