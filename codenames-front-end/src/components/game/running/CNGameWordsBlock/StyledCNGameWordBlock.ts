import styled from "styled-components";
import {CNGameWordsBlock} from "./CNGameWordsBlock";

const wordsInLineCount = new Map<number, number>([
    [30, 5],
    [25, 5],
    [20, 5],
    [16, 4],
])

type StyledProps = {
    wordsCount?: number;
}

export const StyledCNGameWordBlock = styled(CNGameWordsBlock)<StyledProps>`
  --words-line-count: ${props => props.wordsCount ? wordsInLineCount.get(props.wordsCount) : 5};
  --words-width: 170px;
  --words-heigth: 90px;
  
  display: flex;

  flex-direction: row;
  flex-wrap: wrap;
  align-items: center;
  justify-content: center;

  max-width: calc(var(--words-width) * var(--words-line-count) + (20px * var(--words-line-count)));

  transition: ease .2s;

`