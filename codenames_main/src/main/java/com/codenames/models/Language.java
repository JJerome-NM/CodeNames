package com.codenames.models;

public enum Language {
    UA("ua"),
    EN("en");

    private final String strType;

    Language(String strType){
        this.strType = strType;
    }

    public String getStrType() {
        return strType;
    }
}
