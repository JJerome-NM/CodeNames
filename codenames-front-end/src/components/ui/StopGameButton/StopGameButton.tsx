import React from 'react';

type StopGameProps = {
    onClick?: () => void;
    className?: string;
}

const StopGameButton = ({
                            onClick,
                            className
                        }: StopGameProps) => (
    <div className={className} onClick={onClick}>
        <span></span>
    </div>
);

export default StopGameButton;