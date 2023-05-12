import styled from "styled-components";
import BlueYellowBG from "./BlueYellowBG";


export const StyledBlueYellowBG = styled(BlueYellowBG)`
  position: absolute;
  top: 0;
  left: 0;

  z-index: -1;

  overflow: hidden;

  min-height: 100vh;
  min-width: 100vw;

  background-color: #1E1E1E;
`;