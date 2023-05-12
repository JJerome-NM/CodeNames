import React from 'react';

interface CNGameWordProps {
    className?: string;
    children?: React.ReactNode;
}

export const CNGameWord = ({
                               className,
                               children
                           }: CNGameWordProps) => (
    <div className={className}>
        {children}
    </div>
);
