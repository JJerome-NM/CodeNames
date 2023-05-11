import styled, {css} from "styled-components";
import CNTeamSidePanel from "./CNTeamSidePanel";


type StyledProps = {
    teamColor: "blue" | "yellow";
    position: "left" | "right";
}

export const StyledCNTeamSidePanel = styled(CNTeamSidePanel)<StyledProps>`
  --panel-team-color: ${props => `var(--cn-${props.teamColor})`};

  display: flex;

  overflow: hidden;

  flex-wrap: wrap;
  justify-content: space-between;

  min-width: 180px;
  min-height: 200px;

  max-width: 250px;
  
  width: 25vw;
  height: 90vh;

  color: #ffffff;

  background: rgba(0, 0, 0, 0.5);
  border-width: 3px 0 3px 0;
  border-style: solid;
  border-color: var(--panel-team-color);
  backdrop-filter: blur(10px);

  ${props => props.position === "left" ?
          css`
            border-radius: 0 30px 30px 0;
          ` : css`
            border-radius: 30px 0 0 30px;
          `}
`;