import styled from "styled-components";
import RestartGameButton from "./RestartGameButton";


export const StyledRestartGameButton = styled(RestartGameButton)`
  --btn-size: ${props => props.size ? props.size : "30px"};
  
  user-select: none;

  display: flex;

  justify-content: center;
  align-items: center;

  width: var(--btn-size);
  height: var(--btn-size);

  transition: ease .1s;

  &:hover{
    cursor: pointer;
  }

  &:active{
    transform: scale(0.9);
  }
`;