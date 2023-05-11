import styled from "styled-components";
import CNRunButton from "./CNRunButton";


export const StyledCNRunButton = styled(CNRunButton)`
  --btn-size: ${props => props.size ? props.size : "20px"};

  width: var(--btn-size);
  height: var(--btn-size);

  border-radius: 8px;

  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;

  transition: ease .2s;

  &:hover{
    background-color: rgba(55, 55, 55, 0.3);
  }

  &:active{
    transform: scale(0.9);
  }
`;