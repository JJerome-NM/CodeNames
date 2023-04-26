import React, {FC} from 'react';
import css from "./CNMaster.module.css";
import {Color} from "../../../../../models/Color";

interface CNMasterProps {
    color: Color.BLUE | Color.YELLOW
    className?: string;
    onSelect?: () => void;
    children: React.ReactNode;
}

const CNMaster: FC<CNMasterProps> = ({
                                         color,
                                         className,
                                         children,
                                         onSelect
                                     }) => {
    return (
        <div className={[css.TeamMaster, className].join(" ")}>
            <div className={color === Color.BLUE ? css.Blue : css.Yellow}>
                Master -&nbsp;
            </div>
            {children ? children
                :
                <button
                    onClick={onSelect}
                    className={[css.SelectButton, color === Color.BLUE ? css.SelectButtonBlue
                        : css.SelectButtonYellow].join(" ")}
                >
                    Select
                </button>
            }
        </div>
    );
};

export default CNMaster;