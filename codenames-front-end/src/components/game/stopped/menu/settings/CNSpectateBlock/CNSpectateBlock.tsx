import React from 'react';
import css from "./CNSpectateBlock.module.css";

interface CNSpectateButtonProps {
    className?: string;
    onClick?: () => void;
}

const CNSpectateBlock = ({
                             onClick,
                             className
                         }: CNSpectateButtonProps) => {
    return (
        <div className={[css.SpectateBlock, className].join(" ")}>
            <h2 onClick={onClick}>
                Spectate
            </h2>
        </div>
    );
};

export default CNSpectateBlock;