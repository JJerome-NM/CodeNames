package com.codenames.models.room;

import com.codenames.enums.Color;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Word {

    private final int id;

    private final String text;

    private final Color color;

    private boolean hidden = true;

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}
