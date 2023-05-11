import React from 'react';


type PauseButtonProps = {
    onClick?: () => void;
    className?: string;
}

const PauseButton = ({
                         className,
                         onClick
                     }: PauseButtonProps) => (
    <div className={className} onClick={onClick}>
        <span></span>
        <span></span>
    </div>
);

export default PauseButton;