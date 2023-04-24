import React, {Component, FC} from 'react';
import cl from "./bg.module.css"

import firstBgImage from "./images/first_bg_image.svg"
import secondBgImage from "./images/second_bg_image.svg"
import thirdBgImage from "./images/third_bg_image.svg"



const BlueYellowBg: FC = ({...props}) => {
    return (
        <div {...props} className={cl.bg}>
            <img src={firstBgImage} alt=""/>
            <img src={secondBgImage} alt=""/>
            <img src={thirdBgImage} alt=""/>
        </div>
    );
};

export default BlueYellowBg;