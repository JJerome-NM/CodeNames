import React, {CSSProperties, FC, useRef} from 'react';

import css from "./CNGameWordsBlock.module.css"
import CNGameWord from "../CNGameWord/CNGameWord";
import {IWord} from "../../../../models/CodeNames/IWord";

interface CNGameWordsBlockProps {
    words?: IWord[];
    wordsCount?: number;
    hidden?: boolean;
    className?: string;
}

const CNGameWordsBlock: FC<CNGameWordsBlockProps> = ({
                                                         words = [],
                                                         wordsCount = 25,
                                                         hidden = true,
                                                         className
                                                     }) => {

    const block = useRef<HTMLDivElement>(null);

    const calcWordsCountOnLine = (wordCount: number = 20): number => {
        switch (wordCount) {
            case 30:
                return 5;
            case 25:
                return 5;
            case 20:
                return 5;
            case 16:
                return 4;
        }
        return 5;
    }

    return (
        <div
            style={{"--words-line-count": `${calcWordsCountOnLine(wordsCount)}`} as CSSProperties}
            className={[className, css.WordsBlock].join(" ")}
            ref={block}
        >
            {words.map(word =>
                <CNGameWord color={word.color} key={word.id}>{word.text}</CNGameWord>
            )}
        </div>
    );
};

export default CNGameWordsBlock;