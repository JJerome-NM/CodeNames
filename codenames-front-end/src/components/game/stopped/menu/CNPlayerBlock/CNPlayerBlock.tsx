import React, {FC} from 'react';
import {IUser} from "@models/CodeNames/IUser";

import css from "./CNPlayerBlock.module.css"
import {Color} from "@models/CodeNames/Color";
import CNPlayer from "@game/stopped/menu/CNPlayer/CNPlayer";

interface CnPlayerBlockProps {
    color: Color.BLUE | Color.YELLOW;
    players?: IUser[];
    onTeamSelect?: () => void;
    maxPlayers?: number;
    className?: string;
}

const CnPlayerBlock: FC<CnPlayerBlockProps> = ({
                                                   players = [],
                                                   color,
                                                   onTeamSelect,
                                                   maxPlayers = 5,
                                                   className
                                               }) => {
    return (
        <div className={[css.TeamPlayersBlock, className].join(" ")}>
            <div className={[css.TeamPlayerLabel, color === Color.BLUE ? css.Blue : css.Yellow].join(" ")}>
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