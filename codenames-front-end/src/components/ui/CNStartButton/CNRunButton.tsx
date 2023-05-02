import React, {FC} from 'react';

import css from "./CNRunButton.module.css";
import SVGRunImage from "../svg/SVGRunImage";

interface CNStartButtonProps {
    size?: number;
    color?: string;
    onClick?: () => void;
    className?: string;
}

const CNRunButton: FC<CNStartButtonProps> = ({
                                                 size = 20,
                                                 color = "white",
                                                 className,
                                                 onClick
                                             }) => {
    return (
        <div
            className={[css.RunButton, className].join(" ")}
            style={{'--btn-size': `${size}px`} as React.CSSProperties}
            onClick={onClick}
        >
            <SVGRunImage fill={color} size={size}/>
        </div>
    );
};

export default CNRunButton;