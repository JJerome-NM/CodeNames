import styled, {css} from "styled-components";
import {StyledCNDefaultInput, StyledCNDefaultButton} from "../../components";


export const StyledConnectPage = styled.div`
  display: flex;

  justify-content: center;
  align-items: center;

  height: 100vh;
  width: 100vw;

  font-family: "Roboto Light", sans-serif;
`;

export const StyledConnectFormBlock = styled.div`
  display: flex;

  flex-direction: column;
  justify-content: space-between;
  align-items: center;
  overflow: hidden;

  min-width: 425px;
  min-height: 240px;

  border-radius: 25px;

  background: rgba(0, 0, 0, 0.65);
  box-shadow: 0 0 10px 1px rgba(0, 0, 0, 0.25);
  backdrop-filter: blur(5px);
`;

export const StyledConnectFormHeader = styled.div`
  display: flex;

  flex-direction: row;
  user-select: none;

  width: 100%;
`;

type ConnectHeaderPageProps = {
    selected?:boolean;
}

export const StyledConnectHeaderPage = styled.div<ConnectHeaderPageProps>`
  display: flex;

  justify-content: center;

  width: 98%;

  color: #ffffff;
  border-bottom: ${props => props.selected ? "none" : "1px solid rgba(255, 255, 255, 0.25)"};

  font-size: 20px;
  font-weight: normal;

  &:last-child{
    border-left: 1px solid rgba(255, 255, 255, 0.25);
  }

  &:hover{
    cursor: pointer;
  }
`;

const FormStyles = css`
  display: flex;

  justify-content: center;
  align-items: center;
  flex-direction: column;

  width: max-content;
  height: max-content;

  padding: 20px 50px 50px;
`;

export const StyledConnectForm = styled.form`
  ${FormStyles}
`;

export const StyledCreateForm = styled.form`
  ${FormStyles}
`;

export const StyledTitle = styled.h1`
  font-size: 26px;
  font-weight: 400;
  color: #fff;
  margin-bottom: 30px;
`;

export const StyledConnectInput = styled(StyledCNDefaultInput)`
  & input{
    width: 220px;
  }
`;

export const StyledConnectButton = styled(StyledCNDefaultButton)`
  width: 100%;
  margin-top: 20px;
`;

export const StyledCreateButton = styled(StyledCNDefaultButton)`
  width: 100%;
  margin-top: 20px;
  
  background-color: #2858FF;
  color: #ffffff;
`;