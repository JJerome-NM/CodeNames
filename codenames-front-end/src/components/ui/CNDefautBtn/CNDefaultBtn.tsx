import React from "react";

import css from "./CNDefaultBtn.module.css"

interface CBDefaultBtnProps {
    bgColor?: string;
    className?: string;
    children?: React.ReactNode;
}

const CNDefaultBtn = ({
                          bgColor,
                          className,
                          children,
                      }: CBDefaultBtnProps) => {
    return (
        <button
            className={[className, css.CNDefaultBtn].join(" ")}
            style={bgColor ? {backgroundColor: bgColor} : {}}
        >
            {children}
        </button>
    )
}

export default CNDefaultBtn