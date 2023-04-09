package com.codenames.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Language {
    UA("ua"),
    EN("en");

    private final String strType;
}
