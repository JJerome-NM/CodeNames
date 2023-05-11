import styled from "styled-components";
import CNSpectateBlock from "./CNSpectateBlock";


export const StyledCNSpectateBlock = styled(CNSpectateBlock)`
  color: #fff;

  transition: ease .2s;

  &:hover{
    cursor: pointer;
  }

  &:active{
    transform: scale(0.95);
  }
`;