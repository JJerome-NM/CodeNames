import React, {FC} from 'react';
import css from "./AdminControl.module.css";
import CNRunButton from "../../../../../ui/CNStartButton/CNRunButton";
import DropdownSetting from "../DropdownSetting/DropdownSetting";


interface AdminControlProps {
    onClickToRun?: () => void;
    runButtonSize?: number;
    runButtonColor?: string;
    className?: string;
    children?: React.ReactNode;
}


const AdminControl: FC<AdminControlProps> = ({
                                                 onClickToRun,
                                                 className,
                                                 children,
                                                 runButtonColor= "#fff",
                                                 runButtonSize = 35
                                             }) => {
    return (
        <div className={[css.SettingAdminBlock, className].join(" ")}>
            <CNRunButton
                size={runButtonSize}
                color={runButtonColor}
                onClick={onClickToRun}
            />
            <DropdownSetting>
                {children}
            </DropdownSetting>
        </div>
    );
};

export default AdminControl;