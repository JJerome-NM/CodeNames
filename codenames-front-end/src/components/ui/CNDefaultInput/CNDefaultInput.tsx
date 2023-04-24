import React, {FC} from 'react';

import cl from "./CNDefaultInput.module.css"

interface CNDefaultInputProps{
    value?: string | number;
    className?: string;
    onChange?: (e: React.ChangeEvent<any>) => void;
    type?: string;
    placeholder?: string;
}

const CNDefaultInput: FC<CNDefaultInputProps> = ((
    {
        value,
        className,
        onChange,
        type,
        placeholder,
    }) => {
    return (
        <input
            value={value}
            className={[cl.CNDefaultInput, className].join(" ")}
            onChange={onChange}
            type={type}
            placeholder={placeholder}
        />
    )
})

export default CNDefaultInput;