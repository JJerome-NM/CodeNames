import React from 'react';

type CrossProps = {
    className?: string;
}

const Cross = ({
                   className,
               }: CrossProps) => (
    <div className={className}>
        <span></span>
        <span></span>
    </div>
);

export default Cross;