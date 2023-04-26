import React, {Component, FC} from 'react';
import css from "./bg.module.css"

import firstBgImage from "./images/first_bg_image.svg"
import secondBgImage from "./images/second_bg_image.svg"
import thirdBgImage from "./images/third_bg_image.svg"



const BlueYellowBg: FC = ({...props}) => {
    return (
        <div {...props} className={css.bg}>
            <img className={css.bg__image} src={firstBgImage} alt=""/>
            <img className={css.bg__image} src={secondBgImage} alt=""/>
            <img className={css.bg__image} src={thirdBgImage} alt=""/>
        </div>
    );
};

export default BlueYellowBg;