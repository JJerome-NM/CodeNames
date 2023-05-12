import React from 'react';

import {IWord} from "../../../../models";

import {StyledCNGameWord} from "../CNGameWord";

interface CNGameWordsBlockProps {
    words?: IWord[];
    className?: string;
}

export const CNGameWordsBlock = ({
                              words = [],
                              className
                          }: CNGameWordsBlockProps) => (
    <div className={className}>
        {words.map(word =>
            <StyledCNGameWord key={word.id} wordColor={word.color}>
                {word.text}
            </StyledCNGameWord>
        )}
    </div>
);