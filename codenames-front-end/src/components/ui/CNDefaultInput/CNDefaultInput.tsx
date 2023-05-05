import React from 'react';

import css from "./CNDefaultInput.module.css"
import {v4} from "uuid";


interface CNDefaultInputProps {
    value?: string | number;
    inputLabel?: React.ReactNode;
    inputLabelText?: string
    inputLabelID?: string;
    className?: string;
    onChange?: (e: React.ChangeEvent<any>) => void;
    type?: string;
    placeholder?: string;
}

const CNDefaultInput = (({
                             value,
                             inputLabel,
                             inputLabelText,
                             inputLabelID = v4(),
                             className,
                             onChange,
                             type,
                             placeholder,
                         }: CNDefaultInputProps) => {
    return (
        <div className={[className, css.InputBlock].join(" ")}>
            {inputLabel ? inputLabel :
                <label
                    htmlFor={inputLabelID}
                    className={css.InputLabel}
                >
                    {inputLabelText}
                </label>
            }
            <input
                id={inputLabelID}
                value={value}
                className={css.CNDefaultInput}
                onChange={onChange}
                type={type}
                placeholder={placeholder}
            />
        </div>
    )
})

export default CNDefaultInput;