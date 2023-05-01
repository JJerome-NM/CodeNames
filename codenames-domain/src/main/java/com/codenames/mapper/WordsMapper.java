package com.codenames.mapper;

import com.codenames.dto.WordDto;
import com.codenames.enums.Color;
import com.codenames.models.room.Word;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;


@Mapper(componentModel = "spring")
public abstract class WordsMapper {

    @Named("wordToWordDto")
    @Mapping(target = "color", expression = "java(this.getWordColor(word, wordsColorHidden))")
    public abstract WordDto wordToWordDto(Word word, boolean wordsColorHidden);

    Color getWordColor(Word word, boolean wordsColorHidden){
        return !wordsColorHidden || !word.isHidden() ? word.getColor() : Color.DEFAULT;
    }

    @IterableMapping(elementTargetType = WordDto.class)
    List<WordDto> wordsListToWordsDtoList(List<Word> words, boolean wordsColorHidden){
        return words.stream()
                .map(word -> this.wordToWordDto(word, wordsColorHidden))
                .toList();
    }

}
