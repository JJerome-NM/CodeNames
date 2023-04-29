import React, {FC, useState} from 'react';

import BlueYellowBg from "../../components/ui/BlueYellowBG/BlueYellowBG";
import CNDefaultInput from "../../components/ui/CNDefaultInput/CNDefaultInput";
import CNDefaultBtn from "../../components/ui/CNDefautBtn/CNDefaultBtn";

import {useNavigate} from "react-router-dom";
import useFetching from "../../hooks/useFetching";
import {Flip, ToastContainer} from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';
import {notify} from "../../models/notifications/Notifications";
import {CodeNamesGameRestService} from "../../services/CodeNamesGameRestService";

import css from "./styles/main.module.css"

const Connect: FC = () => {
    const minRoomID = 100000;
    const maxRoomID = 1000000;

    const navigate = useNavigate();
    const [selectedForm, setSelectedForm] = useState<"connect" | "create">("connect");
    const [roomID, setRoomID] = useState<number>(minRoomID);

    const [fetchConnectToRoom, isLoadingConnectToRoom, errorConnectToRoom] = useFetching(async () => {
        const response = await CodeNamesGameRestService.tryConnectToRoom(roomID);

        if (response.data === -1) {
            notify.error(`Room with number "${roomID}" was not found`)
        } else {
            navigate(`/room/${roomID}`)
        }
    });

    const [fetchCreateRoom, isLoadingCreateRoom, errorCreateRoom] = useFetching(async () => {
        const response = await CodeNamesGameRestService.createRoom();

        if (response.data === -1) {
            notify.error(`${response.data}`)
        } else {
            navigate(`/room/${response.data}`)
        }
    });

    async function tryConnectToRoom(e: React.FormEvent<HTMLFormElement>) {
        e.preventDefault();

        if (roomID < minRoomID) {
            notify.warn(`Room numbers start from ${minRoomID}`)
        } else if (roomID > maxRoomID) {
            notify.warn(`Room numbers end in ${maxRoomID}`)
        } else {
            await fetchConnectToRoom();
        }
    }

    return (
        <div className={css.ConnectPage}>
            <div className={css.ConnectFormBlock}>
                <div className={css.ConnectFormHeader}>
                    <div
                        className={css.ConnectHeaderPage}
                        style={selectedForm === "connect" ? {borderBottom: "none"} : {}}
                        onClick={() => setSelectedForm("connect")}
                    >
                        Connect
                    </div>
                    <div
                        className={css.ConnectHeaderPage}
                        style={selectedForm === "create" ? {borderBottom: "none"} : {}}
                        onClick={() => setSelectedForm("create")}
                    >
                        Create
                    </div>
                </div>
                <div className={css.ConnectFormBody}>
                    {selectedForm === "connect" ?
                        <form
                            className={css.ConnectForm}
                            onSubmit={tryConnectToRoom}
                        >
                            <h1 className={css.Title}>Connect to room</h1>
                            <CNDefaultInput
                                className={css.ConnectInput}
                                inputLabelText={"Room ID"}
                                value={roomID}
                                onChange={(e: React.ChangeEvent<HTMLInputElement>) => setRoomID(Number(e.target.value))}
                                type="number"
                                placeholder="Write room id"
                            />
                            <CNDefaultBtn className={css.Button}>Connect</CNDefaultBtn>
                        </form>
                        :
                        <form
                            className={css.CreateForm}
                            onSubmit={(e: React.FormEvent<HTMLFormElement>) => {
                                e.preventDefault();
                                fetchCreateRoom();
                            }}
                        >
                            <h1 className={css.Title}>Create new game room</h1>
                            <CNDefaultBtn className={[css.CreateButton, css.Button].join(" ")}>Create</CNDefaultBtn>
                        </form>
                    }
                </div>
            </div>

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
            <BlueYellowBg/>
        </div>)
}

export default Connect;