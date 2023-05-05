import React, {CSSProperties} from 'react';

import css from "./Cross.module.css"

interface CrossProps {
    size?: number;
    lineWidth?: number;
    color?: string;
    className?: string;
}

const Cross = ({
                   className,
                   lineWidth = 3,
                   size = 20,
                   color = "#fff"
               }: CrossProps) => {
    return (
        <div
            style={{
                '--line-width': `${lineWidth}px`,
                '--cross-size': `${size}px`,
                '--cross-color': `${color}`
            } as CSSProperties}
            className={[className, css.Cross].join(" ")}
        >
            <span></span>
            <span></span>
        </div>
    );
};

export default Cross;