import React from 'react';

import {
    StyledPauseButton,
    StyledStopGameButton,
    StyledRestartGameButton
} from "../../../../ui";

interface GRAdminControlBlockProps {
    onClickToGamePause?: () => void;
    onClickToGameStop?: () => void;
    onClickToGameRestart?: () => void;
    className?: string;
}

const GRAdminControlBlock = ({
                                 onClickToGamePause,
                                 onClickToGameStop,
                                 onClickToGameRestart,
                                 className
                             }: GRAdminControlBlockProps) => (
    <div className={className}>
        <StyledPauseButton
            onClick={onClickToGamePause}
            size={30}
        />
        <StyledStopGameButton
            onClick={onClickToGameStop}
            size={30}
        />
        <StyledRestartGameButton
            onClick={onClickToGameRestart}
            size={30}
        />
    </div>
);

export default GRAdminControlBlock;