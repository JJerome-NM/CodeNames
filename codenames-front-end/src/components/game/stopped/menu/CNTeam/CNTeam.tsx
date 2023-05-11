import React from 'react';

import {Color} from "../../../../../models/CodeNames/Color";
import CNPlayerBlock from "../CNPlayerBlock/CNPlayerBlock";
import {ITeam} from "../../../../../models/CodeNames/ITeam";
import {StyledCNMaster} from "../CNMaster/StyledCNMaster";
import {StyledCNTeamName} from "../CNTeamName/StyledCNTeamName";

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