import React from 'react';
import {Color} from "../../../../../models";
import styled from "styled-components";


type WithColorProps = {
    color: Color.BLUE | Color.YELLOW
}

const StyledMasterLabel = styled.div<WithColorProps>`
  color: ${props => `var(--cn-${props.color?.toLowerCase()})`}
`;

const StyledSelectButton = styled.button<WithColorProps>`
  color: #ffffff;
  font-size: 25px;

  user-select: none;

  border: none;
  border-bottom: 2px solid #ffffff;

  transition: ease .2s;

  background: none;
  
  &:hover{
    cursor: pointer;
    color: ${props => `var(--cn-${props.color?.toLowerCase()})`};
    border-bottom: 2px solid ${props => `var(--cn-${props.color?.toLowerCase()})`};
  }
  &:active{
    transform: scale(0.9);
  }
`;

interface CNMasterProps {
    color: Color.BLUE | Color.YELLOW
    className?: string;
    onSelect?: () => void;
    children: React.ReactNode;
}

const CNMaster = ({
                      color,
                      className,
                      children,
                      onSelect
                  }: CNMasterProps) => {
    return (
        <div className={className}>
            <StyledMasterLabel color={color}>Master -&nbsp;</StyledMasterLabel>
            {children ? children
                :
                <StyledSelectButton
                    color={color}
                    onClick={onSelect}
                >
                    Select
                </StyledSelectButton>
            }
        </div>
    );
};

export default CNMaster;