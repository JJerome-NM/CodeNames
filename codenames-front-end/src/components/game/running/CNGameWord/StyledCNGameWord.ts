import {Color} from "../../../../models";
import {CNGameWord} from "./CNGameWord";
import styled, {css} from "styled-components";

const colorStylesMap = new Map<Color, string>([
    [Color.YELLOW, "var(--words-yellow-color)"],
    [Color.BLUE, "var(--words-blue-color)"],
    [Color.BLACK, "var(--words-black-color)"],
    [Color.WHITE, "var(--words-white-color)"]
])

type StyleProps = {
    wordColor: Color;
    className?: string
}

export const StyledCNGameWord = styled(CNGameWord)<StyleProps>`
  user-select: none;

  display: flex;
  justify-content: center;
  align-items: center;
  
  margin: 10px;

  width: var(--words-width);
  height: var(--words-heigth);

  color: ${props => props.wordColor === Color.BLACK ? "#ffffff" : "#1E1E1E"};
  background-color: ${props => props.wordColor ? colorStylesMap.get(props.wordColor) : "var(--words-default-color)"};
  
  font-style: normal;
  font-weight: 600;
  font-size: 1.5rem;
  line-height: 1.55rem;

  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
  border-radius: 20px;
  transition: ease 0.1s;
  
  &:hover{
    cursor: pointer;
  }
  &:active{
    transform: scale(0.95);
  }
  
  ${css`
    @media screen and (max-width: 1340px){
      width: calc(var(--words-width) * 0.8);
      height: calc(var(--words-heigth) * 0.8);
      font-size: calc(1.5rem * 0.8);
    }
  `}
`