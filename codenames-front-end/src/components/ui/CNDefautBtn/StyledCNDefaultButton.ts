import styled from "styled-components";
import CNDefaultBtn from "./CNDefaultBtn";


type CNDefaultButtonProps = {
    backgroundColor?: string
}

export const StyledCNDefaultButton = styled(CNDefaultBtn)<CNDefaultButtonProps>`
  --btn-bg-color: ${props => props.backgroundColor ? props.backgroundColor : "rgba(0, 0, 0, .5)"};

  min-width: 100px;

  padding: 3px 40px;

  font-family: 'Roboto Thin', sans-serif;
  font-weight: 500;
  font-size: 22px;
  line-height: 26px;

  color: #FFFFFF;

  border: none;
  border-radius: 10px;

  transition: ease .2s;

  background-color: var(--btn-bg-color);
  
  &:hover{
    transform: scale(1.05);
    cursor: pointer;
  }
`;