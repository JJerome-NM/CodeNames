import {Status} from "../../../../../models/CodeNames/Status";
import styled, {css} from "styled-components";

type WithStatusProps = {
    status?: Status;
}

export const StyledStopGameMenuBlock = styled.div<WithStatusProps>`
  --stop-menu-padding: 40px;

  position: relative;
  display: flex;
  box-sizing: border-box;

  flex-direction: column;
  justify-content: space-between;
  align-items: center;

  min-height: 200px;
  min-width: 300px;
  width: 60vw;
  height: 70vh;

  transition: ease .5s;

  padding: var(--stop-menu-padding) calc(var(--stop-menu-padding) / 2);
  padding-bottom: 10px;
  border-radius: var(--stop-menu-padding);

  background: rgba(0, 0, 0, 0.4);
  box-shadow: 0 0 10px 1px rgba(0, 0, 0, 0.25);
  backdrop-filter: blur(5px);
  
  ${props => props.status !== Status.STOPPED && css`
    user-select: none;
    pointer-events: none;
    opacity: 0;
  `}
  
  ${css`
    @media screen and (max-width: 900px){
      width: 100vw;
    }
  `}
  
}
`;

export const StyledTeamsBlock = styled.div`
  display: flex;

  justify-content: center;
  align-items: center;

  width: 100%;
  height: 100%;
`;

export const StyledBoundaryLine = styled.hr`
  display: block;

  width: 2px;
  align-self: stretch;

  margin: 0 var(--stop-menu-padding);

  border: none;

  background-color: rgba(255, 255, 255, 0.15);
`;

export const StyledSettingBlock = styled.div`
  display: flex;

  width: 100%;
  height: 40px;

  flex-direction: row;

  justify-content: space-between;
  align-items: center;
`;