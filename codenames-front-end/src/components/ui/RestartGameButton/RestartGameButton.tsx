import React from 'react';

import css from "./RestartGameButton.module.css"
import SVGRestartGame from "../svg/SVGRestartGame";

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
                           }: RestartGameButtonProps) => {
    return (
        <div
            className={[className, css.RestartButton].join(" ")}
            style={{"--btn-size": size} as React.CSSProperties}
            onClick={onClick}
        >
            <SVGRestartGame size={size} fill={fill}/>
        </div>
    );
};

export default RestartGameButton;