import styled from "styled-components";
import {StyledCNBlurredFrom, StyledCNDefaultButton, StyledCNDefaultInput} from "../../components";


export const StyledAuthenticationFrom = styled(StyledCNBlurredFrom)`
  display: flex;

  flex-direction: column;
  justify-content: center;
  align-items: center;

  flex-wrap: wrap;

  padding: 20px 60px;
  border-radius: 40px;
`;

type FormTitleProps = {
    marginBottom?: string;
}

export const StyledAuthenticationFormTitle = styled.div<FormTitleProps>`

  margin-bottom: ${props => props.marginBottom ? props.marginBottom : "0"};
  
  font-size: 1.8rem;
`;

export const StyledAuthenticationInputBlock = styled.div`
  display: flex;

  align-items: end;
  flex-direction: column;
`;

export const StyledAuthenticationInput = styled(StyledCNDefaultInput)`

    margin: 5px;
`;

export const StyledAuthenticationButton = styled(StyledCNDefaultButton)`
    
  width: 90%;
  
  margin-top: 2vh;
`;