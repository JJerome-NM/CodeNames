import React, {useEffect, useRef, useState} from 'react';

import {useOnClickOutside} from "../../../../../../hooks";
import {StyledSettingButton, StyledSettingButtonLine, StyledSettingMenu} from "./DropdownSettingStyles";

interface DropdownSettingProps {
    className?: string;
    children?: React.ReactNode;
}

const DropdownSetting = ({
                             children,
                             className
                         }: DropdownSettingProps) => {
    const openMenu = () => {
        if (!menuIsOpen) {
            setMenuIsOpen(true)
        } else {
            closeMenu()
        }
    }

    const closeMenu = () => {
        if (menuIsOpen) {
            setMenuIsOpen(false)
        }
    }

    const settingMenuRef = useRef<HTMLDivElement>(null)
    const childrenRef = useRef<HTMLDivElement>(null)
    const [menuIsOpen, setMenuIsOpen] = useState<boolean>(false);

    useOnClickOutside(settingMenuRef, closeMenu)

    useEffect(() => {
        if (menuIsOpen) {
            childrenRef.current?.style.setProperty('--top-offset', `-${childrenRef.current?.clientHeight}px`)
        }
    }, [menuIsOpen])

    return (
        <div ref={settingMenuRef} className={className}>
            <StyledSettingButton onClick={openMenu}>
                <StyledSettingButtonLine isOpen={menuIsOpen}/>
                <StyledSettingButtonLine isOpen={menuIsOpen}/>
                <StyledSettingButtonLine isOpen={menuIsOpen}/>
            </StyledSettingButton>

            <StyledSettingMenu isOpen={menuIsOpen} ref={childrenRef}>
                {children}
            </StyledSettingMenu>
        </div>
    );
};

export default DropdownSetting;