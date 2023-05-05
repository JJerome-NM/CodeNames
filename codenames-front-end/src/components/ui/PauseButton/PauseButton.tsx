import React, {CSSProperties} from 'react';

import css from "./PauseButton.module.css"

interface PauseButtonProps {
    size?: number;
    bgColor?: string;
    onClick?: () => void;
    className?: string;
}

const PauseButton = ({
                         size = 30,
                         bgColor = "#ffffff",
                         className,
                         onClick
                     }: PauseButtonProps) => {
    return (
        <div
            className={[className, css.PauseButton].join(" ")}
            onClick={onClick}
            style={{
                '--btn-size': `${size}px`,
                '--btn-bg-color': bgColor
            } as CSSProperties}
        >
            <span></span>
            <span></span>
        </div>
    );
};

export default PauseButton;