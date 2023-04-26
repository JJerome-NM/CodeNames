import React, {FC} from 'react';
import {Color} from "../../../../models/Color";

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
    return (
        <div className={[color, css.Word, className].join(" ")}>
            {children}
        </div>
    );
};

export default CNGameWord;