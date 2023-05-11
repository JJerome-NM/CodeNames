import React from 'react';

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
                     }: CNStartButtonProps) => (
    <div className={className} onClick={onClick}>
        <SVGRunImage fill={color} size={size}/>
    </div>
);

export default CNRunButton;