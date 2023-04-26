import React, {FC, useState} from 'react';

import css from "./styles/main.module.css"

import BlueYellowBg from "../../components/ui/BlueYellowBG/BlueYellowBG";
import CNDefaultInput from "../../components/ui/CNDefaultInput/CNDefaultInput";
import CNDefaultBtn from "../../components/ui/CNDefautBtn/CNDefaultBtn";

import {useNavigate} from "react-router-dom";

const Connect: FC = () => {
    const minRoomID = 100000;
    const maxRoomID = 1000000;

    const router = useNavigate()
    const [roomID, setRoomID] = useState<number>(minRoomID)


    async function tryConnectToRoom(e: React.FormEvent<HTMLFormElement>) {
        e.preventDefault()

        if (roomID < minRoomID || roomID > maxRoomID) {
            return console.log(roomID < minRoomID || roomID > maxRoomID)
        }

        router(`/room/${roomID}`)
    }

    return (
        <div className={css.main}>
            <form
                className={css.myForm}
                onSubmit={tryConnectToRoom}
            >
                <CNDefaultInput
                    value={roomID}
                    onChange={(e: React.ChangeEvent<HTMLInputElement>) => setRoomID(Number(e.target.value))}
                    type="number"
                    placeholder="Write room id"
                />
                <CNDefaultBtn className={css.Button}>Connect</CNDefaultBtn>
            </form>

            <BlueYellowBg/>
        </div>)
}

export default Connect;