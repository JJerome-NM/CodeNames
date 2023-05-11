import React from 'react';
import CNRunButton from "../../../../../ui/CNStartButton/CNRunButton";
import {StyledDropdownSetting} from "../DropdownSetting/StyledDropdownSetting";
import {StyledCNRunButton} from "../../../../../ui/CNStartButton/StyledCNRunButton";


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