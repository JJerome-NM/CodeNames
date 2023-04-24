import React, {useEffect, useRef, useState} from 'react';
import {useLocation, useParams} from "react-router-dom";
import GrayWhiteBG from "../../components/ui/GrayWhiteBG/GrayWhiteBG";
import {IGameRoom} from "../../models/IGameRoom";

import mainCss from './styles/main.module.css'
import CodeNameGameService from "../../services/CodeNameGameService";
import CNPlayerBlock from "../../components/ui/CNPlayerBlock/CNPlayerBlock";
import CNMaster from "../../components/ui/CNMasterBlock/CNMaster";
import CNTeamName from "../../components/ui/CNTeamName/CNTeamName";
import CNTeam from "../../components/ui/CNTeam/CNTeam";
import CNRunButton from "../../components/ui/CNStartButton/CNRunButton";

// const [fetchRoom, isRoomLoading, roomError] = useFetching(async () => {
//     const room = await RoomService.getRoomByID(params.id)
//
//
//
//     console.log(room)
// })

const room: IGameRoom = JSON.parse("{\n" +
    "    \"status\": \"STOPPED\",\n" +
    "    \"gameTurn\": null,\n" +
    "    \"blueTeam\": {\n" +
    "        \"color\": \"BLUE\",\n" +
    "        \"score\": 0,\n" +
    "        \"master\": null,\n" +
    "        \"players\": [\n" +
    "        {\n" +
    "            \"id\": 232343,\n" +
    "            \"nickname\": \"JJerome\"\n" +
    "        },\n" +
    "        {\n" +
    "            \"id\": 545,\n" +
    "            \"nickname\": \"JJerome\"\n" +
    "        },\n" +
    "        {\n" +
    "            \"id\": 6785,\n" +
    "            \"nickname\": \"JJerome\"\n" +
    "        },\n" +
    "        {\n" +
    "            \"id\": 2323343443,\n" +
    "            \"nickname\": \"JJerome\"\n" +
    "        },\n" +
    "        {\n" +
    "            \"id\": 3445,\n" +
    "            \"nickname\": \"JJerome\"\n" +
    "        }\n" +
    "    ],\n" +
    "        \"messages\": []\n" +
    "    },\n" +
    "    \"yellowTeam\": {\n" +
    "        \"color\": \"YELLOW\",\n" +
    "        \"score\": 0,\n" +
    "        \"master\": null,\n" +
    "        \"players\": [],\n" +
    "        \"messages\": []\n" +
    "    },\n" +
    "    \"spectators\": [\n" +
    "        {\n" +
    "            \"id\": 232343,\n" +
    "            \"nickname\": \"JJerome\"\n" +
    "        }\n" +
    "    ],\n" +
    "    \"words\": [],\n" +
    "    \"wordCount\": 0,\n" +
    "    \"timer\": 0\n" +
    "}")

const GameRoom = () => {

    const params = useParams();

    const socketService = useRef<CodeNameGameService>()

    useEffect(() => {
        socketService.current = new CodeNameGameService()

    }, [])

    return (
        <div className={mainCss.CodeNamesGameFrame}>

            <div className={mainCss.StopGameMenu}>
                <div className={mainCss.TeamsBlock}>
                    <CNTeam
                        team={room.blueTeam}
                        onMasterSelect={() => {socketService.current?.createRoom()}}
                        onTeamSelect={() => {console.log("Blue player")}}
                    />

                    <hr className={mainCss.BoundaryLine}/>

                    <CNTeam
                        team={room.yellowTeam}
                    />
                </div>
                <div className={mainCss.SettingBlock}>

                    <div className={mainCss.SpectateBlock}>
                        <h2>Spectate</h2>
                    </div>

                    <div className={mainCss.SettingAdminBlock}>
                        <CNRunButton onClick={() => console.log("Run")}/>
                    </div>
                </div>
            </div>

            {/*<button onClick={() => socketService.current?.createRoom()}>Connect</button>*/}

            <GrayWhiteBG/>
        </div>
    );
};

export default GameRoom;