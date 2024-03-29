import React, {useEffect} from 'react';
import {IGameRoom, Status, Color} from "../../../../../models";
import {CodeNameWsRoomRequests} from "../../../../../hooks";
import {StyledCNTeam} from "../CNTeam";
import {StyledBoundaryLine, StyledSettingBlock, StyledStopGameMenuBlock, StyledTeamsBlock} from "./CNStopGameMenuStyles";
import {StyledAdminControl, StyledCNSpectateBlock} from "../settings";


interface CNStopGameMenuProps {
    room?: IGameRoom;
    requests?: CodeNameWsRoomRequests;
    className?: string;
}

const CNStopGameMenu = ({
                            room,
                            className,
                            requests
                        }: CNStopGameMenuProps) => {
    const [menuDisplay, setMenuDisplay] = React.useState<string>("flex");
    const [timeoutId, setTimeoutId] = React.useState<number>(0);

    useEffect(() => {
        clearTimeout(timeoutId);

        if (room?.status && room?.status !== Status.STOPPED) {
            setTimeoutId(setTimeout(() => {
                setMenuDisplay("none");
            }, 500));
        } else {
            setMenuDisplay("flex");
        }
    }, [room?.status])

    return (
        <div style={{display: menuDisplay}}>
            <StyledStopGameMenuBlock status={room?.status} className={[className, "NEGR"].join(" ")}>
                <StyledTeamsBlock>
                    <StyledCNTeam
                        team={room?.blueTeam}
                        onMasterSelect={() => requests?.selectMaster(Color.BLUE)}
                        onTeamSelect={() => requests?.joinToTeam(Color.BLUE)}
                    />

                    <StyledBoundaryLine/>

                    <StyledCNTeam
                        team={room?.yellowTeam}
                        onMasterSelect={() => requests?.selectMaster(Color.YELLOW)}
                        onTeamSelect={() => requests?.joinToTeam(Color.YELLOW)}
                    />
                </StyledTeamsBlock>
                <StyledSettingBlock>
                    <StyledCNSpectateBlock
                        onClick={() => requests?.joinToSpectator()}
                    />

                    <StyledAdminControl
                        onClickToRun={() => requests?.startGame()}
                        runButtonSize={35}
                    >
                        <div>Start time</div>
                        <div>Turn time</div>
                        <div>Words count</div>
                        <div>Language</div>
                    </StyledAdminControl>
                </StyledSettingBlock>
            </StyledStopGameMenuBlock>
        </div>
    );
};

export default CNStopGameMenu;