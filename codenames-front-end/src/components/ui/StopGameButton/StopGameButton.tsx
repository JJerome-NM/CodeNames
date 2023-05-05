import React, {CSSProperties} from 'react';

import css from "./StopGameButton.module.css"


interface StopGameProps {
    onClick?: () => void;
    size?: number;
    bgColor?: string;
    className?: string;
}

const StopGameButton = ({
                            bgColor,
                            size = 30,
                            onClick,
                            className
                        }: StopGameProps) => {
    return (
        <div
            className={[className, css.StopGame].join(" ")}
            style={{
                '--stop-bg-color': bgColor,
                '--stop-btn-size': `${size}px`
            } as CSSProperties}
            onClick={onClick}
        >
            <span className={css.StopGameImage}></span>
        </div>
    );
};

export default StopGameButton;