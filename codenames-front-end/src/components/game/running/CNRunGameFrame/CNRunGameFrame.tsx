import React, {useEffect} from 'react';

import {Status} from "../../../../models/CodeNames/Status";
import CNTeamSidePanel from "../CNSidePanel/CNTeamSidePanel/CNTeamSidePanel";
import {IGameRoom} from "../../../../models/CodeNames/IGameRoom";
import {StyledCNGameWordBlock} from "../CNGameWordsBlock/StyledCNGameWordBlock";
import {StyledCNTeamSidePanel} from "../CNSidePanel/CNTeamSidePanel/StyledCNTeamSidePanel";

interface CNRunGameFrameProps {
    room?: IGameRoom;
    className?: string;
}

const CNRunGameFrame = ({
                            room,
                            className
                        }: CNRunGameFrameProps) => {
    const [menuDisplay, setMenuDisplay] = React.useState<string>("none");
    const [timeoutId, setTimeoutId] = React.useState<number>(0);

    useEffect(() => {
        clearTimeout(timeoutId);
        if (room) {
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
            className={className}
            style={{display: menuDisplay}}
        >
            {room?.blueTeam &&
                <StyledCNTeamSidePanel
                    team={room?.blueTeam}
                    teamName={"Blue"}
                    teamColor={"blue"}
                    position={"left"}
                />}

            <StyledCNGameWordBlock
                words={room?.words}
                wordsCount={room?.wordsCount}
            />

            {room?.yellowTeam &&
                <StyledCNTeamSidePanel
                    team={room?.yellowTeam}
                    teamName={"Yellow"}
                    teamColor={"yellow"}
                    position={"right"}
                />}
        </div>
    );
};

export default CNRunGameFrame;