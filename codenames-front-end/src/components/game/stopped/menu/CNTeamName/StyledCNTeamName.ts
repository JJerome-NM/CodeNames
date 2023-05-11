import styled from "styled-components";
import CNTeamName from "./CNTeamName";
import {Color} from "../../../../../models/CodeNames/Color";


type StyledProps = {
    color: Color.BLUE | Color.YELLOW;
}

export const StyledCNTeamName = styled(CNTeamName)<StyledProps>`
  display: flex;

  justify-content: center;
  align-items: center;

  margin-bottom: 20px;

  color: ${props => `var(--cn-${props.color?.toLowerCase()})`};
  
  font-family: 'Roboto', sans-serif;
  font-style: normal;
  font-weight: 600;
  font-size: 30px;
  line-height: 35px;
`