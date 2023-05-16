package com.codenames.mapper;

import com.codenames.dto.WordDto;
import com.codenames.enums.Color;
import com.codenames.domain.room.Word;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;


@Mapper(componentModel = "spring")
public interface WordsMapper {

    @Named("wordToWordDto")
    @Mapping(target = "color", expression = "java(this.getWordColor(word, wordsColorHidden))")
    WordDto wordToWordDto(Word word, boolean wordsColorHidden);

    default Color getWordColor(Word word, boolean wordsColorHidden){
        return !wordsColorHidden || !word.isHidden() ? word.getColor() : Color.DEFAULT;
    }

    @IterableMapping(elementTargetType = WordDto.class)
    default List<WordDto> wordsListToWordsDtoList(List<Word> words, boolean wordsColorHidden){
        return words.stream()
                .map(word -> this.wordToWordDto(word, wordsColorHidden))
                .toList();
    }

}
