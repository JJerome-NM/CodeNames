import styled, {css} from "styled-components";
import CNRunGameFrame from "./CNRunGameFrame";


type StyledProps = {
    hidden?: boolean;
}

export const StyledCNRunGameFrame = styled(CNRunGameFrame)<StyledProps>`
  display: flex;
  position: absolute;

  justify-content: center;
  align-items: center;

  width: 100vw;
  height: 100vh;

  transition: ease .5s;

  ${props => props.hidden && css`
      opacity: 0;
      user-select: none;
      pointer-events: none;
  `}
`