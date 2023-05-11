import styled, {css} from "styled-components";
import CNTeamSidePanel from "./CNTeamSidePanel";


type StyledProps = {
    teamColor: "blue" | "yellow";
    position: "left" | "right";
}

export const StyledCNTeamSidePanel = styled(CNTeamSidePanel)<StyledProps>`
  --panel-team-color: ${props => `var(--cn-${props.teamColor})`};

  position: absolute;
  display: flex;

  overflow: hidden;

  flex-wrap: wrap;

  min-width: 100px;
  min-height: 200px;

  width: 200px;
  height: 800px;

  color: #ffffff;

  background: rgba(0, 0, 0, 0.5);
  border-width: 3px 0 3px 0;
  border-style: solid;
  border-color: var(--panel-team-color);
  backdrop-filter: blur(10px);

  ${props => props.position === "left" ?
          css`
            left: 0;
            border-radius: 0 30px 30px 0;
          ` : css`
            right: 0;
            border-radius: 30px 0 0 30px;
          `}
`;