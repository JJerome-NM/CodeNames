import React, {FC, useEffect, useRef, useState} from 'react';

import css from "./SettingButton.module.css"
import {useOnClickOutside} from "@hooks/useOnClickOutside";

interface DropdownSettingProps {
    className?: string;
    children?: React.ReactNode;
}

const DropdownSetting: FC<DropdownSettingProps> = ({
                                                   children,
                                                   className
                                               }) => {
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
        <div ref={settingMenuRef} className={[css.SettingBlock, className].join(" ")}>
            <div className={[css.SettingButton, menuIsOpen ? css.Opened : ""].join(" ")} onClick={openMenu}>
                <span className={css.SettingButtonLine}></span>
                <span className={css.SettingButtonLine}></span>
                <span className={css.SettingButtonLine}></span>
            </div>

            <div className={[!menuIsOpen ? css.Hidden : "", css.SettingMenu].join(" ")} ref={childrenRef}>
                {children}
            </div>
        </div>
    );
};

export default DropdownSetting;