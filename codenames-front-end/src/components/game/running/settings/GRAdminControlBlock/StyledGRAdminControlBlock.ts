import styled, {css} from "styled-components";
import GRAdminControlBlock from "./GRAdminControlBlock";


type StyledProps = {
    hidden?: boolean;
}

export const StyledGRAdminControlBlock = styled(GRAdminControlBlock)<StyledProps>`
  display: flex;
  position: absolute;

  justify-content: space-between;
  align-items: center;

  bottom: 5px;

  width: 110px;

  ${props => props.hidden && css`
      opacity: 0;
      user-select: none;
      pointer-events: none;
  `}
`;