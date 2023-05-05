import React, {useEffect} from 'react';
import {IGameRoom} from "../../../../../models/CodeNames/IGameRoom";
import css from "./CnStopGameMenu.module.css";
import {Color} from "../../../../../models/CodeNames/Color";
import {Status} from "../../../../../models/CodeNames/Status";
import CNSpectateBlock from "../settings/CNSpectateBlock/CNSpectateBlock";
import CNTeam from "../CNTeam/CNTeam";
import AdminControl from "../settings/AdminControl/AdminControl";
import {CodeNameWsRoomRequests} from "../../../../../hooks/useCodeNamesWsRoomConnect";


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
        <div
            className={css.StopGameMenuBlock}
            style={{display: menuDisplay}}
        >
            <div
                className={[
                    className,
                    room?.status !== Status.STOPPED ? css.Hidden : "",
                    css.StopGameMenu
                ].join(" ")}
            >
                <div className={css.TeamsBlock}>
                    <CNTeam
                        team={room?.blueTeam}
                        onMasterSelect={() => requests?.selectMaster(Color.BLUE)}
                        onTeamSelect={() => requests?.joinToTeam(Color.BLUE)}
                    />

                    <hr className={css.BoundaryLine}/>

                    <CNTeam
                        team={room?.yellowTeam}
                        onMasterSelect={() => requests?.selectMaster(Color.YELLOW)}
                        onTeamSelect={() => requests?.joinToTeam(Color.YELLOW)}
                    />
                </div>
                <div className={css.SettingBlock}>
                    <CNSpectateBlock
                        onClick={() => requests?.joinToSpectator()}
                    />

                    <AdminControl
                        onClickToRun={() => requests?.startGame()}
                        runButtonSize={35}
                    >
                        <div>Start time</div>
                        <div>Turn time</div>
                        <div>Words count</div>
                        <div>Language</div>
                    </AdminControl>
                </div>
            </div>
        </div>
    );
};

export default CNStopGameMenu;