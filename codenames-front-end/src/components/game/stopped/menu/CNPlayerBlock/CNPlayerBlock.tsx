import React from 'react';

import css from "./CNPlayerBlock.module.css"
import CNPlayer from "../CNPlayer/CNPlayer";
import {Color} from "../../../../../models/CodeNames/Color";
import {IUser} from "../../../../../models/CodeNames/IUser";

interface CnPlayerBlockProps {
    color: Color.BLUE | Color.YELLOW;
    players?: IUser[];
    onTeamSelect?: () => void;
    maxPlayers?: number;
    className?: string;
}

const CnPlayerBlock = ({
                           players = [],
                           color,
                           onTeamSelect,
                           maxPlayers = 5,
                           className
                       }: CnPlayerBlockProps) => {
    return (
        <div className={[
            className,
            css.TeamPlayersBlock
        ].join(" ")}>
            <div className={[
                css.TeamPlayerLabel,
                color === Color.BLUE ? css.Blue : css.Yellow
            ].join(" ")}>
                <div>Players</div>
                {players.length >= maxPlayers ? ""
                    :
                    <button onClick={onTeamSelect} className={css.JoinButton}>
                        Join
                    </button>
                }
            </div>
            <ul className={css.TeamPlayers}>
                {players.map(user =>
                    <CNPlayer key={user.id} user={user} type={"li"}/>)
                }
            </ul>
        </div>
    );
};

export default CnPlayerBlock;