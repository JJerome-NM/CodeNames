import React, {FC} from 'react';
import css from "./CnTeamName.module.css";
import {Color} from "../../../../../models/CodeNames/Color";

interface CnTeamNameProps{
    color: Color.BLUE | Color.YELLOW;
    children: React.ReactNode;
}

const CnTeamName: FC<CnTeamNameProps> = ({color, children}) => {
    return (
        <div className={[css.TeamName, color === Color.BLUE ? css.Blue : css.Yellow].join(" ")}>
            {children}
        </div>
    );
};

export default CnTeamName;