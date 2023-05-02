import React, {FC} from 'react';

import css from "./GRAdminControlBlock.module.css"
import PauseButton from "@ui/PauseButton/PauseButton";
import StopGameButton from "@ui/StopGameButton/StopGameButton";
import RestartGameButton from "@ui/RestartGameButton/RestartGameButton";

interface GRAdminControlBlockProps {
    onClickToGamePause?: () => void;
    onClickToGameStop?: () => void;
    onClickToGameRestart?: () => void;
    hidden?: boolean;
    className?: string;
}

const GRAdminControlBlock: FC<GRAdminControlBlockProps> = ({
                                                               onClickToGamePause,
                                                               onClickToGameStop,
                                                               onClickToGameRestart,
                                                               hidden = true,
                                                               className
                                                           }) => {
    return (
        <div className={[className, css.AdminControl, hidden ? css.Hidden : ""].join(" ")}>
            <PauseButton
                onClick={onClickToGamePause}
                size={30}
            />
            <StopGameButton
                onClick={onClickToGameStop}
                size={30}
            />
           <RestartGameButton
                onClick={onClickToGameRestart}
                size={30}
           />
        </div>
    );
};

export default GRAdminControlBlock;