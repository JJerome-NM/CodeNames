import React, {CSSProperties} from 'react';

import css from './CNTeamSidePanel.module.css'
import {ITeam} from "../../../../../models/CodeNames/ITeam";
import CNTeamPlayerBlock from "../CNTeamPlayerBlock/CNTeamPlayerBlock";


interface CNTeamSidePanelProps {
    hidden?: boolean;
    team: ITeam;
    teamName: string;
    teamColor: "blue" | "yellow";
    position: "left" | "right";
    className?: string;
}

const CNTeamSidePanel = ({
                             hidden,
                             team,
                             teamName,
                             teamColor,
                             className,
                             position
                         }: CNTeamSidePanelProps) => {
    return (
        <div
            className={[
                className,
                position === "left" ? css.LeftSidePanel : css.RightSidePanel,
                css.TeamPanel
            ].join(" ")}
            style={{'--panel-team-color': `var(--cn-${teamColor})`} as CSSProperties}
        >
            <div className={[css.PanelBlock, css.TeamNameBlock].join(" ")}>
                <div className={css.TeamPanelName}>{teamName}</div>
                <div className={css.TeamPanelScore}>{team.score}</div>
            </div>
            <CNTeamPlayerBlock
                master={team.master}
                players={team.players}
                message={team.message}
            />
        </div>
    );
};

export default CNTeamSidePanel;