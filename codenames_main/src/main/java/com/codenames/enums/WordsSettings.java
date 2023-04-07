package com.codenames.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum WordsSettings {
    SMALL(16, 8, 6, 1, 1),
    NORMAL(20, 10, 8, 1, 1),
    LARGE(25, 12, 10, 2, 1),
    VERY_LARGE(30, 15, 12, 2, 1);

    private final int wordsCount;

    private final int firstTeamWordsCount;

    private final int secondTeamWordsCount;

    private final int whiteWordsCount;

    private final int blackWordsCount;

}
