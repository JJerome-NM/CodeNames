import styled from "styled-components";
import CNBlurredFrom from "./CNBluredFrom";


type StyledProps = {
    width?: string;
    height?: string;
    borderRadius?: string;
}

export const StyledCNBlurredFrom = styled(CNBlurredFrom)<StyledProps>`
  display: flex;
  
  justify-content: center;
  align-content: center;
  
  width: ${props => props.width ? props.width : "max-content"};
  height: ${props => props.height ? props.height : "max-content"};

  color: #ffffff;
  
  background: rgba(0, 0, 0, 0.65);
  box-shadow: 0 0 10px 1px rgba(0, 0, 0, 0.25);
  backdrop-filter: blur(5px);

  border-radius: ${props => props.borderRadius ? props.borderRadius : "20px"};
  padding: ${props => props.borderRadius ? props.borderRadius : "20px"};
`;