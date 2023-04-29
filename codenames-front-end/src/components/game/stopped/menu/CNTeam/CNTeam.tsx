import React, {FC} from 'react';
import CNTeamName from "../CNTeamName/CNTeamName";
import CNMaster from "../CNMaster/CNMaster";
import CNPlayerBlock from "../CNPlayerBlock/CNPlayerBlock";
import {ITeam} from "../../../../../models/CodeNames/ITeam";
import {Color} from "../../../../../models/CodeNames/Color";

import css from "./CNTeam.module.css"

interface CNTeamProps {
    team?: ITeam;
    onMasterSelect?: () => void;
    onTeamSelect?: () => void;
    className?: string;
}

const CnTeam: FC<CNTeamProps> = ({
                                     team,
                                     className,
                                     onMasterSelect,
                                     onTeamSelect
                                 }) => {
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