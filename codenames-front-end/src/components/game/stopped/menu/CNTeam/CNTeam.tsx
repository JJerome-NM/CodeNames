import React from 'react';

import css from "./CNTeam.module.css"
import {Color} from "../../../../../models/CodeNames/Color";
import CNTeamName from "../CNTeamName/CNTeamName";
import CNMaster from "../CNMaster/CNMaster";
import CNPlayerBlock from "../CNPlayerBlock/CNPlayerBlock";
import {ITeam} from "../../../../../models/CodeNames/ITeam";

interface CNTeamProps {
    team?: ITeam;
    onMasterSelect?: () => void;
    onTeamSelect?: () => void;
    className?: string;
}

const CnTeam = ({
                    team,
                    className,
                    onMasterSelect,
                    onTeamSelect
                }: CNTeamProps) => {

    const teamColor: Color.BLUE | Color.YELLOW = team?.color === "BLUE" || team?.color === "YELLOW"
        ? team.color : Color.BLUE;

    return (
        <div className={[className, css.Team].join(" ")}>
            <CNTeamName color={teamColor}>
                {team?.color === Color.BLUE ? "Blue" : "Yellow"}
            </CNTeamName>
            <CNMaster color={teamColor} onSelect={onMasterSelect}>
                {team?.master?.nickname}
            </CNMaster>
            <CNPlayerBlock
                color={teamColor}
                players={team?.players}
                onTeamSelect={onTeamSelect}
            />
        </div>
    );
};

export default CnTeam;