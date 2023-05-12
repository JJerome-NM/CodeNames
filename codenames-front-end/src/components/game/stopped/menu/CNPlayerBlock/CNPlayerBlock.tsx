import React from 'react';

import {Color, IUser} from "../../../../../models";
import {StyledCNPlayer} from "../CNPlayer";
import {StyledJoinButton, StyledTeamPlayerLabel, StyledTeamPlayers} from "./CNPlayerBlockStyles";

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
        <div className={className}>
            <StyledTeamPlayerLabel color={color}>
                <div>Players</div>
                {players.length >= maxPlayers ? ""
                    :
                    <StyledJoinButton onClick={onTeamSelect} color={color}>
                        Join
                    </StyledJoinButton>
                }
            </StyledTeamPlayerLabel>
            <StyledTeamPlayers>
                {players.map(user =>
                    <StyledCNPlayer key={user.id} user={user} type={"li"}/>)
                }
            </StyledTeamPlayers>
        </div>
    );
};

export default CnPlayerBlock;