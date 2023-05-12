import React from 'react';

import {IUser} from "../../../../../models";

interface CNPlayerProps {
    user: IUser;
    type?: "div" | "li"
    className?: string;
}

const CNPlayer = ({
                      user,
                      type,
                      className
                  }: CNPlayerProps) =>
    type === "div" ? (
        <div className={className}>
            {user.nickname}
        </div>
    ) : (
        <li className={className}>
            {user.nickname}
        </li>
    )

export default CNPlayer;