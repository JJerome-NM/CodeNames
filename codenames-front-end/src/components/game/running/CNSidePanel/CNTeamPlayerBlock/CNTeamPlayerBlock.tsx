import React from 'react';
import {v4} from "uuid";


import {IUser} from "../../../../../models/CodeNames/IUser";
import {
    StyledTeamPanelMaster, StyledTeamPanelMessages,
    StyledTeamPanelMessagesBlock, StyledTeamPanelPlayers,
    StyledTeamPanelPlayersBlock, StyledTextTeamColor
} from "./PlayerBlockComponentsStyles";

interface CNTeamPlayerBlockProps {
    master?: IUser;
    players?: IUser[];
    message?: string[];
    className?: string;
}

const CNTeamPlayerBlock = ({
                               master,
                               players,
                               message,
                               className
                           }: CNTeamPlayerBlockProps) => {
    return (
        <div className={className}>
            <StyledTeamPanelMaster>
                <StyledTextTeamColor>
                    Master
                </StyledTextTeamColor>

                <div>{master && master.nickname}</div>
            </StyledTeamPanelMaster>
            <StyledTeamPanelPlayersBlock>
                <StyledTextTeamColor>
                    Players
                </StyledTextTeamColor>

                <StyledTeamPanelPlayers>
                    {players && players.map(player =>
                        <li key={player.id}>{player.nickname}</li>
                    )}
                </StyledTeamPanelPlayers>
            </StyledTeamPanelPlayersBlock>
            <StyledTeamPanelMessagesBlock>
                <StyledTextTeamColor>
                    Messages
                </StyledTextTeamColor>

                <StyledTeamPanelMessages>
                    {message && message.map(message =>
                        <li key={v4()}>{message}</li>
                    )}
                </StyledTeamPanelMessages>
            </StyledTeamPanelMessagesBlock>
        </div>
    );
};

export default CNTeamPlayerBlock;