import React, {FC, useEffect} from 'react';
import {IGameRoom} from "../../../../models/CodeNames/IGameRoom";
import CNTeamSidePanel from "../CNSidePanel/CNTeamSidePanel/CNTeamSidePanel";
import CNGameWordsBlock from "../CNGameWordsBlock/CNGameWordsBlock";

import css from './CNRunGameFrame.module.css'
import {Status} from "../../../../models/CodeNames/Status";

interface CNRunGameFrameProps {
    room?: IGameRoom
    className?: string;
}

const CNRunGameFrame: FC<CNRunGameFrameProps> = ({
                                                     room,
                                                     className
                                                 }) => {
    const [menuDisplay, setMenuDisplay] = React.useState<string>("none");
    const [timeoutId, setTimeoutId] = React.useState<number>(0);

    useEffect(() => {
        clearTimeout(timeoutId);

        if (room){
            if (room?.status !== Status.RUN) {
                setTimeoutId(setTimeout(() => {
                    setMenuDisplay("none");
                }, 500));
            } else {
                setMenuDisplay("flex");
            }
        } else {
            setMenuDisplay("none");
        }
    }, [room?.status])

    return (
        <div
            className={[
                className,
                room?.status !== Status.RUN ? css.Hidden : "",
                css.RunGameFrame
            ].join(" ")}
            style={{display: menuDisplay}}
        >
            {room?.blueTeam &&
                <CNTeamSidePanel
                    team={room?.blueTeam}
                    teamName={"Blue"}
                    teamColor={"blue"}
                    position={"left"}
                />}

            <CNGameWordsBlock
                words={room?.words}
                wordsCount={room?.wordsCount}
            />

            {room?.yellowTeam &&
                <CNTeamSidePanel
                    team={room?.yellowTeam}
                    teamName={"Yellow"}
                    teamColor={"yellow"}
                    position={"right"}
                />}
        </div>
    );
};

export default CNRunGameFrame;