import React, {FC} from 'react';
import {Color} from "../../../../models/CodeNames/Color";

import css from "./CNGameWord.module.css"

interface CNGameWordProps {
    color?: Color;
    className?: string;
    children?: React.ReactNode;
}

const CNGameWord: FC<CNGameWordProps> = ({
                                             color,
                                             className,
                                             children
                                         }) => {

    let cssClassColor;

    switch (color) {
        case Color.YELLOW: cssClassColor = css.YELLOW; break;
        case Color.BLUE: cssClassColor = css.BLUE; break;
        case Color.BLACK: cssClassColor = css.BLACK; break;
        case Color.WHITE: cssClassColor = css.WHITE; break;
    }

    return (
        <div className={[cssClassColor, css.Word, className].join(" ")}>
            {children}
        </div>
    );
};

export default CNGameWord;