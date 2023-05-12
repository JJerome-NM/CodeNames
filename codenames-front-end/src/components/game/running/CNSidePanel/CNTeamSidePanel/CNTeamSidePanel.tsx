import React from 'react';

import {ITeam} from "../../../../../models";
import {StyledCNTeamPlayerBlock} from "../CNTeamPlayerBlock";
import styled, {css} from "styled-components";


const FontStyle = css`
  font-style: normal;
  font-weight: 700;
  font-size: 40px;
  line-height: 47px;
`

const StyledPanelBlock = styled.div`
  display: flex;

  flex-direction: column;
  align-items: center;
  justify-content: center;

  height: 25%;
  width: 100%;
`;

const StyledTeamPanelName = styled.div`
  ${FontStyle};
  color: var(--panel-team-color)
`

const StyledTeamPanelScore = styled.div`
  ${FontStyle};
  color: #FFFFFF;
`

interface CNTeamSidePanelProps {
    team: ITeam;
    teamName: string;
    className?: string;
}

const CNTeamSidePanel = ({
                             team,
                             teamName,
                             className,
                         }: CNTeamSidePanelProps) => {
    return (
        <div className={className}>
            <StyledPanelBlock>
                <StyledTeamPanelName>{teamName}</StyledTeamPanelName>
                <StyledTeamPanelScore>{team.score}</StyledTeamPanelScore>
            </StyledPanelBlock>
            <StyledCNTeamPlayerBlock
                master={team.master}
                players={team.players}
                message={team.message}
            />
        </div>
    );
};

export default CNTeamSidePanel;