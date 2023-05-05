import React from 'react';
import css from "./CNMaster.module.css";
import {Color} from "../../../../../models/CodeNames/Color";

interface CNMasterProps {
    color: Color.BLUE | Color.YELLOW
    className?: string;
    onSelect?: () => void;
    children: React.ReactNode;
}

const CNMaster = ({
                      color,
                      className,
                      children,
                      onSelect
                  }: CNMasterProps) => {
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