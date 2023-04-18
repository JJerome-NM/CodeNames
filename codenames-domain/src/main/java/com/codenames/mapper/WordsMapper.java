package com.codenames.mapper;

import com.codenames.dto.WordDto;
import com.codenames.enums.Color;
import com.codenames.enums.PlayerRole;
import com.codenames.models.game.Player;
import com.codenames.models.room.Word;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface WordsMapper {

    WordsMapper INSTANCE = Mappers.getMapper(WordsMapper.class);


    @Named("wordToWordDto")
    @Mapping(target = "color", expression = "java(this.getWordColor(word, player))")
    WordDto wordToWordDto(Word word, Player player);

    default Color getWordColor(Word word, Player player){
        return player.getPlayerRole() == PlayerRole.BLUE_MASTER
                || player.getPlayerRole() == PlayerRole.YELLOW_MASTER
                ? word.getColor() : Color.DEFAULT;
    }

    @IterableMapping(elementTargetType = WordDto.class)
    default List<WordDto> wordsListToWordsDtoList(List<Word> words, Player player){
        return words.stream()
                .map(word -> this.wordToWordDto(word, player))
                .toList();
    }

}
