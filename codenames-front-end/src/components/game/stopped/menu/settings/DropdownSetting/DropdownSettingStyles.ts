import styled, {css} from "styled-components";


export const StyledSettingButton = styled.div`
  position: relative;
  display: flex;

  flex-direction: column;

  justify-content: space-between;
  align-items: center;

  width: 35px;
  height: 35px;

  border-radius: 5px;

  transition: ease .2s;

  &:hover {
    background-color: rgba(55, 55, 55, 0.3);
  }

  &:active {
    transform: scale(0.9);
  }
`;

type SettingButtonLineProps = {
    isOpen?: boolean;
}

export const StyledSettingButtonLine = styled.span<SettingButtonLineProps>`
  position: absolute;
  display: block;

  width: 100%;
  height: 6px;

  border-radius: 6px;

  transition: ease .2s;

  background-color: #D9D9D9;

  &:last-child{
    top: 0;
  }

  &:nth-child(2){
    top: calc(50% - 3px);
  }

  &:first-child{
    bottom: 0;
  }
  
  ${props => props.isOpen && css`
    background: #C30000;

    &:last-child {
      top: calc(50% - 3px);
      transform: rotateZ(45deg);
      opacity: 0;
    }

    &:nth-child(2) {
      top: calc(50% - 3px);
      transform: rotateZ(45deg);
    }

    &:first-child {
      top: calc(50% - 2px);
      transform: rotateZ(-45deg);
    }
  `}
}
`;

type SettingMenuProps = {
    isOpen?: boolean;
}

export const StyledSettingMenu = styled.div<SettingMenuProps>`
  --top-offset: 0;

  position: absolute;

  width: max-content;

  top: calc(var(--top-offset) - 10px);
  right: 0;

  padding: 5px 10px;

  background: #1D1D1D;
  border: 1px solid #000000;
  box-shadow: 0 0 10px #000000;
  border-radius: 10px;
  
  ${props => !props.isOpen && css`
    user-select: none;
    pointer-events: none;
    opacity: 0;
  `}
`;