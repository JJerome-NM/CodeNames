import styled from "styled-components";
import StopGameButton from "./StopGameButton";


type StopGameProps = {
    size?: number;
    bgColor?: string;
}

export const StyledStopGameButton = styled(StopGameButton)<StopGameProps>`
  --stop-bg-color: ${props => props.bgColor ? props.bgColor : "#ffffff"};
  --stop-btn-size: ${props => props.size ? `${props.size}px` : "30px"};

  transition: ease .1s;

  &:hover{
    cursor: pointer;
  }

  &:active{
    transform: scale(0.9);
  }

  & span{
    display: block;

    width: var(--stop-btn-size);
    height: var(--stop-btn-size);

    border-radius: 25%;

    background-color: var(--stop-bg-color);
  }
`;