import React from 'react';

import StopGameButton from "../../../../ui/StopGameButton/StopGameButton";
import {StyledPauseButton} from "../../../../ui/PauseButton/StyledPauseButton";
import {StyledRestartGameButton} from "../../../../ui/RestartGameButton/StyledRestartGameButton";
import {StyledStopGameButton} from "../../../../ui/StopGameButton/StyledStopGameButton";

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