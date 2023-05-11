import styled from "styled-components";
import {Color} from "../../../../../models/CodeNames/Color";


type WithColorProps = {
    color: Color
}

export const StyledTeamPlayerLabel = styled.div<WithColorProps>`
  --team-color: ${props => props.color === Color.BLUE ? "var(--cn-blue)" : "var(--cn-yellow)"};
  
  display: flex;
  width: max-content;

  margin-bottom: 5px;

  color: var(--team-color);
  
  font-family: 'Roboto', sans-serif;
  font-style: normal;
  font-weight: 600;
  font-size: 30px;
  line-height: 35px;
`;

export const StyledJoinButton = styled.button<WithColorProps>`
  user-select: none;

  margin-left: 10px;

  color: #ffffff;
  font-size: 25px;

  border: none;
  border-bottom: 2px solid #ffffff;

  transition: ease .2s;

  background: none;
  
  &:hover{
    cursor: pointer;
    color: var(--team-color);
    border-bottom: 2px solid var(--team-color);
  }
  &:active{
    transform: scale(0.9);
  }
`;

export const StyledTeamPlayers = styled.ul`
  list-style: none;
`;