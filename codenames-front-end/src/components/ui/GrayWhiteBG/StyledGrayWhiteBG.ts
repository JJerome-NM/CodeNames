import styled from "styled-components";
import GrayWhiteBG from "./GrayWhiteBG";


export const StyledGrayWhiteBG = styled(GrayWhiteBG)`
  position: absolute;
  top: 0;
  left: 0;

  z-index: -1;

  overflow: hidden;

  user-select: none;

  min-height: 100vh;
  min-width: 100vw;

  background-color: #1E1E1E;
  
  & img{
    position: absolute;
    left: 0;
    bottom: 0;

    background-size: cover;
    background-position: center;
  }
`;