import React, {useEffect} from 'react';

import {Status, IGameRoom} from "../../../../models";
import {StyledCNGameWordBlock} from "../CNGameWordsBlock";
import {StyledCNTeamSidePanel} from "../CNSidePanel";

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
    }, [room])

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