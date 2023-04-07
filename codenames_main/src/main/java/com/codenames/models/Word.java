package com.codenames.models;

import com.codenames.dto.WordDto;
import com.codenames.enums.Color;
import com.codenames.enums.PlayerRole;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Word {

    private final int id;

    private final String text;

    private final Color color;

    private boolean hidden = true;

    public WordDto toWordDto(PlayerRole playerRole){
        if (playerRole == PlayerRole.BLUE_MASTER || playerRole == PlayerRole.YELLOW_MASTER){
            return new WordDto(this.id, this.text, this.color);
        } else if (this.hidden){
            return new WordDto(this.id, this.text, Color.DEFAULT);
        } else {
            return new WordDto(this.id, this.text, this.color);
        }
    }
}
