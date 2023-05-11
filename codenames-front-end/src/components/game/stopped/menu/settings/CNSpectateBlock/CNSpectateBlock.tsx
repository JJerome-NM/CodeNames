import React from 'react';

interface CNSpectateButtonProps {
    className?: string;
    onClick?: () => void;
}

const CNSpectateBlock = ({
                             onClick,
                             className
                         }: CNSpectateButtonProps) => {
    return (
        <div className={className}>
            <h2 onClick={onClick}>
                Spectate
            </h2>
        </div>
    );
};

export default CNSpectateBlock;