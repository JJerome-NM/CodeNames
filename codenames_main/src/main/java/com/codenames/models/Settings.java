package com.codenames.models;

public class Settings {

    private Language language = Language.UA;

    private int wordsCount = 20;

    private int startTime = 90;

    private int turnTime = 60;

    private int timeReward = 15;

    Settings(){}

    public Language getLanguage() {
        return language;
    }

    public int getWordsCount() {
        return wordsCount;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getTurnTime() {
        return turnTime;
    }

    public int getTimeReward() {
        return timeReward;
    }
}
