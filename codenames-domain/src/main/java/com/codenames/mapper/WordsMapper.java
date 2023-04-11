package com.codenames.mapper;

import com.codenames.dto.WordDto;
import com.codenames.enums.Color;
import com.codenames.enums.PlayerRole;
import com.codenames.models.forgame.Player;
import com.codenames.models.forooms.Word;

import java.util.List;

public class WordsMapper {

    public static WordDto wordToWordDto(Word word, Player player){
        if (player.getPlayerRole() == PlayerRole.BLUE_PLAYER
                || player.getPlayerRole() == PlayerRole.YELLOW_PLAYER) {
            return new WordDto(word.getId(), word.getText(), Color.DEFAULT);
        }
        return new WordDto(word.getId(), word.getText(), word.getColor());
    }

    public static List<WordDto> wordsToWordsDtoList(List<Word> words, Player player){
        return words.stream().map(word -> wordToWordDto(word, player)).toList();
    }

}
