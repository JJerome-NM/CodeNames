import React from 'react';

import css from "./CNRunButton.module.css";
import SVGRunImage from "../svg/SVGRunImage";

interface CNStartButtonProps {
    size?: number;
    color?: string;
    onClick?: () => void;
    className?: string;
}

const CNRunButton = ({
                         size = 20,
                         color = "white",
                         className,
                         onClick
                     }: CNStartButtonProps) => {
    return (
        <div
            className={[className, css.RunButton].join(" ")}
            style={{'--btn-size': `${size}px`} as React.CSSProperties}
            onClick={onClick}
        >
            <SVGRunImage fill={color} size={size}/>
        </div>
    );
};

export default CNRunButton;