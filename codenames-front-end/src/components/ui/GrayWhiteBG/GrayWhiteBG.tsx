import React, {FC} from 'react';

import cl from "./GrayWhiteBG.module.css"

import grayBgImage from "./images/gray-bg-image-1.svg"
import whiteBgImage from "./images/white-bg-image-2.svg"
import darkBgImage from "./images/dark-bg-image-3.svg"

interface GrayYellowBgProps{
    className?: string;
}

const GrayYellowBg: FC<GrayYellowBgProps> = ({className, ...props}) => {
    return (
        <div {...props} className={[cl.bg, className].join(" ")}>
            <img className={cl.bg__image} src={darkBgImage} alt="dark bg"/>
            <img className={cl.bg__image} src={whiteBgImage} alt="white bg"/>
            <img className={cl.bg__image} src={grayBgImage} alt="gray bg"/>
        </div>
    );
};

export default GrayYellowBg;