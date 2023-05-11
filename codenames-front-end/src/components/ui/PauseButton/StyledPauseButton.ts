import styled from "styled-components";
import PauseButton from "./PauseButton";

type PauseButtonProps = {
    size?: number;
    bgColor?: string;
}

export const StyledPauseButton = styled(PauseButton)<PauseButtonProps>`
  --btn-size: ${props => props.size ? `${props.size}px` : "30px"};
  --btn-bg-color: ${props => props.bgColor ? props.bgColor : "#fff"};

  display: flex;

  justify-content: space-around;

  width: var(--btn-size);
  height: var(--btn-size);

  transition: ease .1s;

  &:hover{
    cursor: pointer;
  }

  &:active{
    transform: scale(0.9);
  }


  & span{

    display: block;

    width: calc(var(--btn-size) / 3);
    height: var(--btn-size);

    border-radius: 1000px;

    background-color: var(--btn-bg-color);
  }
`;