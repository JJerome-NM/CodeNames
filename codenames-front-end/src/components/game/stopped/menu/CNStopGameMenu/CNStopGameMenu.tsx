import React, {FC} from 'react';
import {IGameRoom} from "../../../../../models/CodeNames/IGameRoom";
import css from "./CnStopGameMenu.module.css";
import {Color} from "../../../../../models/CodeNames/Color";
import CodeNameGameWebSocketService from "../../../../../services/CodeNameGameWebSocketService";
import {Status} from "../../../../../models/CodeNames/Status";
import CNSpectateBlock from "../settings/CNSpectateBlock/CNSpectateBlock";
import CNTeam from "../CNTeam/CNTeam";
import AdminControl from "../settings/AdminControl/AdminControl";

interface CNStopGameMenuProps {
    room?: IGameRoom;
    service?: CodeNameGameWebSocketService;
    className?: string;
}

const CNStopGameMenu: FC<CNStopGameMenuProps> = ({
                                                     room,
                                                     className,
                                                     service
                                                 }) => {
    return (
        <div className={css.StopGameMenuBlock}>
            <div
                className={[room?.status !== Status.STOPPED ? css.Hidden : "", css.StopGameMenu, className].join(" ")}
            >
                <div className={css.TeamsBlock}>
                    <CNTeam
                        team={room?.blueTeam}
                        onMasterSelect={() => service?.selectMaster(Color.BLUE)}
                        onTeamSelect={() => service?.joinToTeam(Color.BLUE)}
                    />

                    <hr className={css.BoundaryLine}/>

                    <CNTeam
                        team={room?.yellowTeam}
                        onMasterSelect={() => service?.selectMaster(Color.YELLOW)}
                        onTeamSelect={() => service?.joinToTeam(Color.YELLOW)}
                    />
                </div>
                <div className={css.SettingBlock}>
                    <CNSpectateBlock
                        onClick={service?.joinToSpectator}
                    />

                    <AdminControl
                        className={css.Test23}
                        onClickToRun={() => service?.startGame()}
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