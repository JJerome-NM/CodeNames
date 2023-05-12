import React from 'react';
import {StyledDropdownSetting} from "../DropdownSetting";
import {StyledCNRunButton} from "../../../../../ui";


interface AdminControlProps {
    onClickToRun?: () => void;
    runButtonSize?: number;
    runButtonColor?: string;
    className?: string;
    children?: React.ReactNode;
}


const AdminControl = ({
                          onClickToRun,
                          className,
                          children,
                          runButtonColor = "#fff",
                          runButtonSize = 35
                      }: AdminControlProps) => (
    <div className={className}>
        <StyledCNRunButton
            size={runButtonSize}
            color={runButtonColor}
            onClick={onClickToRun}
        />
        <StyledDropdownSetting>
            {children}
        </StyledDropdownSetting>
    </div>
);

export default AdminControl;