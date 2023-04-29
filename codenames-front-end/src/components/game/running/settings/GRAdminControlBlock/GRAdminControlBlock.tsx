import React, {FC} from 'react';

import css from "./GRAdminControlBlock.module.css"
import SVGRestartGame from "../../../../ui/svg/SVGRestartGame";
import PauseButton from "../../../../ui/PauseButton/PauseButton";

interface GRAdminControlBlockProps {
    onClickToGamePause?: () => void;
    onClickToGameStop?: () => void;
    onClickToGameRestart?: () => void;
    className?: string;
}

const GRAdminControlBlock: FC<GRAdminControlBlockProps> = ({
                                                               onClickToGamePause,
                                                               onClickToGameStop,
                                                               onClickToGameRestart,
                                                               className
                                                           }) => {
    return (
        <div className={[className, css.AdminControl].join(" ")}>
            <PauseButton
                onClick={onClickToGamePause}
            />
            <button>Stop</button>
            <div onClick={onClickToGameRestart}>
                <SVGRestartGame size={37} fill={"#fff"}/>
            </div>
        </div>
    );
};

export default GRAdminControlBlock;