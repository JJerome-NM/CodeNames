import React from 'react';

import {v4} from "uuid";
import styled from "styled-components";


const StyledInputLabel = styled.label`
  margin-right: 10px;

  font-size: 22px;
  font-weight: 400;
`;

const StyledCNDefaultInput = styled.input`
  padding: 0 5px;

  border: 0;
  border-bottom: 2px solid white;

  color: #c0c0c0;
  background: none;

  font-family: 'Roboto Light', sans-serif;
  font-style: normal;
  font-weight: 300;
  font-size: 22px;
  line-height: 26px;

  text-align: center;

  transition: ease .25s;

  &:focus{
    outline: none;
    background-color: rgba(0, 0, 0, .4);
    color: #ffffff;
  }


  &[type=number]::-webkit-inner-spin-button,
  &[type=number]::-webkit-outer-spin-button{
    -webkit-appearance: none;
  }
`;


type CNDefaultInputProps = {
    value?: string | number;
    inputLabel?: React.ReactNode;
    inputLabelText?: string
    inputLabelID?: string;
    className?: string;
    onChange?: (e: React.ChangeEvent<any>) => void;
    type?: string;
    placeholder?: string;
}

const CNDefaultInput = (({
                             value,
                             inputLabel,
                             inputLabelText,
                             inputLabelID = v4(),
                             className,
                             onChange,
                             type,
                             placeholder,
                         }: CNDefaultInputProps) => (
        <div className={className}>
            {inputLabel ? inputLabel :
                <StyledInputLabel htmlFor={inputLabelID}>
                    {inputLabelText}
                </StyledInputLabel>
            }
            <StyledCNDefaultInput
                id={inputLabelID}
                value={value}
                onChange={onChange}
                type={type}
                placeholder={placeholder}
            />
        </div>
    )
)

export default CNDefaultInput;