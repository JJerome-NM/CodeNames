import React, {CSSProperties} from 'react';

import CNGameWord from "../CNGameWord/CNGameWord";
import {IWord} from "../../../../models/CodeNames/IWord";

import css from "./CNGameWordsBlock.module.css"

interface CNGameWordsBlockProps {
    words?: IWord[];
    wordsCount?: number;
    hidden?: boolean;
    className?: string;
}

const wordsInLineCount = new Map<number, number>([
    [30, 5],
    [25, 5],
    [20, 5],
    [16, 4],
])

const CNGameWordsBlock = ({
                              words = [],
                              wordsCount = 25,
                              hidden,
                              className
                          }: CNGameWordsBlockProps) => {
    return (
        <div
            style={{"--words-line-count": `${wordsInLineCount.get(wordsCount)}`} as CSSProperties}
            className={[className, css.WordsBlock].join(" ")}
        >
            {words.map(word =>
                <CNGameWord color={word.color} key={word.id}>{word.text}</CNGameWord>
            )}
        </div>
    );
};

export default CNGameWordsBlock;