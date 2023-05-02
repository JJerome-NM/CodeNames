import React, {FC} from 'react';

import css from "./CNPlayer.module.css"
import {IUser} from "../../../../../models/CodeNames/IUser";

interface CNPlayerProps{
    user: IUser;
    type?: "div" | "li"
    className?: string;
}

const CNPlayer: FC<CNPlayerProps> = ({user, type, className}) => {
    return type === "div"
        ? (
            <div className={[css.CNPlayer, className].join(" ")}>
                {user.nickname}
            </div>
        ) : (
            <li className={[css.CNPlayer, className].join(" ")}>
                {user.nickname}
            </li>
        )
};

export default CNPlayer;