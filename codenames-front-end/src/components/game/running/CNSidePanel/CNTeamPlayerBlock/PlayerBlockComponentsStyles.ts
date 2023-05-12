import styled, {css} from "styled-components";


const GeneralStyleFroSomeComponents = css`
  display: flex;

  flex-direction: column;

  height: max-content;
  width: 100%;

  font-style: normal;
  font-weight: 700;
  font-size: 25px;
  line-height: 29px;
`;

const GeneralScrollbarStyle = css`
  &::-webkit-scrollbar{
    width: 6px;

    border-radius: 6px;

    background-color: rgba(0, 0, 0, 0.5);
  }
  &::-webkit-scrollbar-thumb{
    border-radius: 6px;

    background-color: rgba(255, 255, 255, 0.7);
  }
  
  &::-webkit-scrollbar-thumb:hover{
    background-color: #fff;
  }
`

export const StyledTeamPanelMaster = styled.div`
  ${GeneralStyleFroSomeComponents};
  height: 15%;
`;

export const StyledTeamPanelPlayersBlock = styled.div`
  ${GeneralStyleFroSomeComponents};
  height: 35%;
  margin-bottom: 20px;
`;

export const StyledTeamPanelMessagesBlock = styled.div`
  ${GeneralStyleFroSomeComponents};
  width: 100%;
  height: 40%;
`;

export const StyledTextTeamColor = styled.div`
  color: var(--panel-team-color);
`;

export const StyledTeamPanelMessages = styled.ul`
  overflow: auto;

  width: 100%;
  height: 100%;
  ${GeneralScrollbarStyle};
`;

export const StyledTeamPanelPlayers = styled.ul`
  overflow: auto;

  height: 90%;
`;