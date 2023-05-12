import React from 'react';

import SVGRestartGame from "../../assets/SVGRestartGame";

interface RestartGameButtonProps {
    size?: number;
    fill?: string;
    onClick?: () => void;
    className?: string;
}

const RestartGameButton = ({
                               size = 30,
                               fill = "#fff",
                               onClick,
                               className
                           }: RestartGameButtonProps) => (
    <div className={className} onClick={onClick}>
        <SVGRestartGame size={size} fill={fill}/>
    </div>
);

export default RestartGameButton;