import React from 'react';

interface CnTeamNameProps {
    className?: string;
    children: React.ReactNode;
}

const CnTeamName = ({
                        className,
                        children
                    }: CnTeamNameProps) => (
    <div className={className}>
        {children}
    </div>
);

export default CnTeamName;