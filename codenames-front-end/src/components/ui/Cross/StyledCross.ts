import styled from "styled-components";
import Cross from "./Cross";


type CrossProps = {
    size?: number;
    lineWidth?: number;
    color?: string;
}

export const StyledCross = styled(Cross)<CrossProps>`
  --line-width: ${props => props.lineWidth ? props.lineWidth : "3px"};
  --cross-size: ${props => props.size ? props.size : "20px"};
  --cross-color: ${props => props.color ? props.color : "#D9D9D9"};

  width: var(--cross-size);
  height: var(--cross-size);

  border-radius: var(--line-width);

  transition: ease .15s;

  & span{
    position: absolute;
    display: block;

    width: 100%;
    height: var(--line-width);

    border-radius: var(--line-width);

    transition: ease .15s;

    background-color: var(--cross-color);
    top: calc(50% - var(--line-width) / 2)
  }

  & span:first-child{
    transform: rotateZ(-45deg);
  }

  & span:last-child{
    transform: rotateZ(45deg);
  }
`;