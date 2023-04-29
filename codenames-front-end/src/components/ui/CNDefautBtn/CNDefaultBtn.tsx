import React, {CSSProperties, FC} from "react";

import css from "./CNDefaultBtn.module.css"

interface CBDefaultBtnProps {
    bgColor?: string;
    className?: string;
    children?: React.ReactNode;
}

const CNDefaultBtn: FC<CBDefaultBtnProps> = ({
                                                 bgColor,
                                                 className,
                                                 children,
                                                 ...props
                                             }) => {
    return (
        <button
            {...props}
            className={[className, css.CNDefaultBtn].join(" ")}
            style={bgColor ? {backgroundColor: bgColor} : {}}
        >
            {children}
        </button>
    )
}

export default CNDefaultBtn