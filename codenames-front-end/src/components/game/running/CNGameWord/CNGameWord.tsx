import React from 'react';

import css from "./CNGameWord.module.css"
import {Color} from "../../../../models/CodeNames/Color";

interface CNGameWordProps {
    color: Color;
    className?: string;
    children?: React.ReactNode;
}

const colorStylesMap = new Map<Color, string>([
    [Color.YELLOW, css.YELLOW],
    [Color.BLUE, css.BLUE],
    [Color.BLACK, css.BLACK],
    [Color.WHITE, css.WHITE]
])

const CNGameWord = ({
                        color,
                        className,
                        children
                    }: CNGameWordProps) => {
    return (
        <div className={[
            className,
            colorStylesMap.get(color),
            css.Word
        ].join(" ")}>
            {children}
        </div>
    );
};

export default CNGameWord;