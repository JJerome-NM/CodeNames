import React, {useState} from 'react';

import {useNavigate} from "react-router-dom";
import {Flip, ToastContainer} from "react-toastify";
import {notify} from "../../models";
import useFetching from "../../hooks/useFetching";

import 'react-toastify/dist/ReactToastify.css';
import {useCodeNamesRestRequests} from "../../hooks";
import {
    StyledConnectButton,
    StyledConnectForm,
    StyledConnectFormBlock,
    StyledConnectFormHeader,
    StyledConnectHeaderPage,
    StyledConnectInput,
    StyledConnectPage,
    StyledCreateButton,
    StyledCreateForm,
    StyledTitle
} from "../Connect";
import {StyledBlueYellowBG} from "../../components";

export const Connect = () => {
    const minRoomID = 100000;
    const maxRoomID = 1000000;

    const navigate = useNavigate();
    const [selectedForm, setSelectedForm] = useState<"connect" | "create">("connect");
    const [roomID, setRoomID] = useState<string>(String(minRoomID));

    const [createRoom, connectToRoom] = useCodeNamesRestRequests();

    const [fetchConnectToRoom, isLoadingConnectToRoom, errorConnectToRoom] = useFetching(async () => {
        const response = await connectToRoom(Number(roomID));

        if (response.data === -1) {
            notify.error(`Room with number "${roomID}" was not found`)
        } else {
            navigate(`/room/${roomID}`)
        }
    });

    const [fetchCreateRoom, isLoadingCreateRoom, errorCreateRoom] = useFetching(async () => {
        const response = await createRoom();

        if (response.data === -1) {
            notify.error(`${response.data}`)
        } else {
            navigate(`/room/${response.data}`)
        }
    });

    async function tryConnectToRoom(e: React.FormEvent<HTMLFormElement>) {
        e.preventDefault();

        const tempRoomID = Number(roomID);

        if (tempRoomID < minRoomID) {
            notify.warn(`Room numbers start from ${minRoomID}`)
        } else if (tempRoomID > maxRoomID) {
            notify.warn(`Room numbers end in ${maxRoomID}`)
        } else {
            await fetchConnectToRoom();
        }
    }

    return (
        <StyledConnectPage>
            <StyledConnectFormBlock>
                <StyledConnectFormHeader>
                    <StyledConnectHeaderPage
                        selected={selectedForm === "connect"}
                        onClick={() => setSelectedForm("connect")}
                    >
                        Connect
                    </StyledConnectHeaderPage>
                    <StyledConnectHeaderPage
                        selected={selectedForm === "create"}
                        onClick={() => setSelectedForm("create")}
                    >
                        Create
                    </StyledConnectHeaderPage>
                </StyledConnectFormHeader>
                <div>
                    {selectedForm === "connect" ?
                        <StyledConnectForm
                            onSubmit={tryConnectToRoom}
                        >
                            <StyledTitle>Connect to room</StyledTitle>
                            <StyledConnectInput
                                inputLabelText={"Room ID"}
                                value={roomID}
                                onChange={(e: React.ChangeEvent<HTMLInputElement>) => setRoomID(e.target.value)}
                                type="number"
                                placeholder="Write room id"
                            />
                            <StyledConnectButton>Connect</StyledConnectButton>
                        </StyledConnectForm>
                        :
                        <StyledCreateForm
                            onSubmit={(e: React.FormEvent<HTMLFormElement>) => {
                                e.preventDefault();
                                fetchCreateRoom();
                            }}
                        >
                            <StyledTitle>Create new game room</StyledTitle>
                            <StyledCreateButton>
                                Create
                            </StyledCreateButton>
                        </StyledCreateForm>
                    }
                </div>
            </StyledConnectFormBlock>

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
            <StyledBlueYellowBG/>
        </StyledConnectPage>)
}