package com.codenames.models.forooms;

import com.codenames.dto.WordDto;
import com.codenames.enums.Color;
import com.codenames.enums.PlayerRole;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Word {

    private final int id;

    private final String text;

    private final Color color;

    private boolean hidden = true;

    public void selectWord(){
        this.hidden = false;
    }
}
