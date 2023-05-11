import React from "react";


interface CBDefaultBtnProps {
    className?: string;
    children?: React.ReactNode;
}

const CNDefaultBtn = ({
                          className,
                          children,
                      }: CBDefaultBtnProps) => (
    <button className={className}>
        {children}
    </button>
)

export default CNDefaultBtn