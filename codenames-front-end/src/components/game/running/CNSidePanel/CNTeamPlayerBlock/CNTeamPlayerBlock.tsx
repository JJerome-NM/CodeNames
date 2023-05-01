import React, {FC} from 'react';
import {v4} from "uuid";
import {IUser} from "../../../../../models/CodeNames/IUser";


import css from "./CnTeamPlayerBlock.module.css";

interface CNTeamPlayerBlockProps {
    master?: IUser;
    players?: IUser[];
    message?: string[];
    className?: string;
}

const CNTeamPlayerBlock: FC<CNTeamPlayerBlockProps> = ({
                                                           master,
                                                           players,
                                                           message,
                                                           className
                                                       }) => {
    return (
        <div className={[className, css.PanelBlock, css.TeamPlayersBlock].join(" ")}>
            <div className={css.TeamPanelMaster}>
                <div className={css.TextTeamColor}>Master</div>

                <div>{master && master.nickname}</div>
            </div>
            <div className={css.TeamPanelPlayersBlock}>
                <div className={css.TextTeamColor}>Players</div>

                <ul className={css.TeamPanelPlayers}>
                    {players && players.map(player =>
                        <li key={player.id}>{player.nickname}</li>
                    )}
                </ul>
            </div>
            <div className={css.TeamPanelMessagesBlock}>
                <div className={css.TextTeamColor}>Messages</div>

                <ul className={css.TeamPanelMessages}>
                    {message && message.map(message =>
                        <li key={v4()}>{message}</li>
                    )}
                </ul>
            </div>
        </div>
    );
};

export default CNTeamPlayerBlock;