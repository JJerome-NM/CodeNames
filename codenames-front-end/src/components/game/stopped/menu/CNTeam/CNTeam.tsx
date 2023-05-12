import React from 'react';

import {Color, ITeam} from "../../../../../models";
import CNPlayerBlock from "../CNPlayerBlock/CNPlayerBlock";
import {StyledCNMaster} from "../CNMaster";
import {StyledCNTeamName} from "../CNTeamName";

interface CNTeamProps {
    team?: ITeam;
    onMasterSelect?: () => void;
    onTeamSelect?: () => void;
    className?: string;
}

const CNTeam = ({
                    team,
                    className,
                    onMasterSelect,
                    onTeamSelect
                }: CNTeamProps) => {

    const teamColor: Color.BLUE | Color.YELLOW = team?.color ? team?.color : Color.BLUE;

    return (
        <div className={className}>
            <StyledCNTeamName color={teamColor}>
                {team?.color === Color.BLUE ? "Blue" : "Yellow"}
            </StyledCNTeamName>
            <StyledCNMaster color={teamColor} onSelect={onMasterSelect}>
                {team?.master?.nickname}
            </StyledCNMaster>
            <CNPlayerBlock
                color={teamColor}
                players={team?.players}
                onTeamSelect={onTeamSelect}
            />
        </div>
    );
};

export default CNTeam;