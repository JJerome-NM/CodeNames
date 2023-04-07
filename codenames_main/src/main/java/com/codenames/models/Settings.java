package com.codenames.models;

import com.codenames.enums.Language;
import com.codenames.enums.WordsSettings;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Settings {

    private Language language = Language.UA;

    private WordsSettings wordsSettings = WordsSettings.LARGE;

    private int startTime = 90;

    private int turnTime = 60;

    private int timeReward = 15;
}
