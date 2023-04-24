import React, {FC} from 'react';
import CNPlayer from "../CNPlayer/CNPlayer";
import {IUser} from "../../../models/IUser";

import css from "./CNPlayerBlock.module.css"
import {Color} from "../../../models/Color";

interface CnPlayerBlockProps {
    color: Color.BLUE | Color.YELLOW;
    players: IUser[];
    onTeamSelect?: () => void;
    maxPlayers?: number;
    className?: string;
}

const CnPlayerBlock: FC<CnPlayerBlockProps> = ({
                                                   players,
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