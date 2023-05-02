import React, {FC} from 'react';
import SVGRestartGame from "@ui/svg/SVGRestartGame";

import css from "./RestartGameButton.module.css"

interface RestartGameButtonProps {
    size?: number;
    fill?: string;
    onClick?: () => void;
    className?: string;
}

const RestartGameButton: FC<RestartGameButtonProps> = ({
                                                           size = 30,
                                                           fill = "#fff",
                                                           onClick,
                                                           className
                                                       }) => {
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