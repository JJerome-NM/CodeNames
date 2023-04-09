package com.codenames.services;


import com.codenames.enums.Color;
import com.codenames.enums.Language;
import com.codenames.models.for_rooms.Settings;
import com.codenames.models.for_rooms.Word;
import com.codenames.utils.IntegerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;

public class WordsService {

    private WordsService() throws IllegalAccessException {
        throw new IllegalAccessException("Service class");
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(WordsService.class);

    private static final String RESOURCES_PATH = "codenames-domain/src/main/resources/";

    private static final String FILE_NAME = "game-words-";

    private static List<String> readWordsFile(Language language){
        List<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(
                RESOURCES_PATH + FILE_NAME + language.getStrType() + ".txt"))) {

            String line;
            while((line = reader.readLine()) != null){
                words.add(line);
            }

        } catch (IOException exception){
            LOGGER.error(exception.getMessage());
        }

        return words;
    }

    public static List<Word> generateRandomWords(Settings settings){
        List<String> tempWords = readWordsFile(settings.getLanguage());
        List<Word> resultWords = new ArrayList<>();

        BiConsumer<Integer, Color> wordsRandomizer = (wordsCount, color) -> {
            int randWordIndex;
            for (int createdWordsCount = 0; createdWordsCount < wordsCount; createdWordsCount++){
                randWordIndex = IntegerUtils.randInt(0, tempWords.size() - 1);

                resultWords.add(new Word(randWordIndex, tempWords.get(randWordIndex), color));

                tempWords.remove(randWordIndex);
            }
        };

        int whiteWordsCount = settings.getWordsSettings().getWhiteWordsCount();
        int blackWordsCount = settings.getWordsSettings().getBlackWordsCount();
        int blueTeamWordsCount;
        int yellowTeamWordsCount;

        if (IntegerUtils.randInt(0, 100) > 50){
            blueTeamWordsCount = settings.getWordsSettings().getFirstTeamWordsCount();
            yellowTeamWordsCount = settings.getWordsSettings().getSecondTeamWordsCount();
        } else {
            blueTeamWordsCount = settings.getWordsSettings().getSecondTeamWordsCount();
            yellowTeamWordsCount = settings.getWordsSettings().getFirstTeamWordsCount();
        }

        wordsRandomizer.accept(blueTeamWordsCount, Color.BLUE);
        wordsRandomizer.accept(yellowTeamWordsCount, Color.YELLOW);
        wordsRandomizer.accept(whiteWordsCount, Color.WHITE);
        wordsRandomizer.accept(blackWordsCount, Color.BLACK);

        Collections.shuffle(resultWords);
        return resultWords;
    }

}
