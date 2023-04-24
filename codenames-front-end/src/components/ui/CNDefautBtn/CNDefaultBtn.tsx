import React, {FC} from "react";

import cl from "./CNDefaultBtn.module.css"

interface CBDefaultBtnProps{
    className?: string;
    children?: React.ReactNode;
}

const CNDefaultBtn: FC<CBDefaultBtnProps> = ({className, children, ...props}) => {
    return (
        <button {...props} className={[cl.CNDefaultBtn, className].join(" ")}>
            {children}
        </button>
    )
}

export default CNDefaultBtn