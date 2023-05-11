import React from 'react';

import firstBgImage from "./images/first_bg_image.svg"
import secondBgImage from "./images/second_bg_image.svg"
import thirdBgImage from "./images/third_bg_image.svg"
import styled from "styled-components";


const StyledBGImage = styled.img`
  position: absolute;
  left: 0;
  bottom: 0;

  background-size: cover;
  background-position: center;
`;

type BlueYellowBgProps = {
    className?: string
}

const BlueYellowBg = ({
                          className
                      }: BlueYellowBgProps) => (
    <div className={className}>
        <StyledBGImage src={firstBgImage} alt=""/>
        <StyledBGImage src={secondBgImage} alt=""/>
        <StyledBGImage src={thirdBgImage} alt=""/>
    </div>
);

export default BlueYellowBg;