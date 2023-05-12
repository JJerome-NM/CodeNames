import React from 'react';

import grayBgImage from "./images/gray-bg-image-1.svg"
import whiteBgImage from "./images/white-bg-image-2.svg"
import darkBgImage from "./images/dark-bg-image-3.svg"

interface GrayYellowBgProps {
    className?: string;
}

const GrayWhiteBG = ({
                          className,
                      }: GrayYellowBgProps) => {
    return (
        <div className={className}>
            <img src={darkBgImage} alt="dark bg"/>
            <img src={whiteBgImage} alt="white bg"/>
            <img src={grayBgImage} alt="gray bg"/>
        </div>
    );
};

export default GrayWhiteBG;