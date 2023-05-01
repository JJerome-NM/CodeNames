import React, {CSSProperties, FC} from 'react';

import css from "./StopGameButton.module.css"


interface StopGameProps {
    onClick?: () => void;
    size?: number;
    bgColor?: string;
    className?: string;
}

const StopGameButton: FC<StopGameProps> = ({
                                               bgColor,
                                               size = 30,
                                               onClick,
                                               className
                                           }) => {
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