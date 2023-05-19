package com.codenames.services;


import com.codenames.enums.Color;
import com.codenames.enums.Language;
import com.codenames.domain.room.Settings;
import com.codenames.domain.room.Word;
import com.codenames.properties.CodeNamesProperties;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;

@Service
@RequiredArgsConstructor
public class WordsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WordsService.class);

    private final Random random = new Random();

    private final CodeNamesProperties codeNamesProperties;

    private List<String> readWordsFile(Language language){
        List<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(codeNamesProperties.getWords().getResourcesPath()
                + codeNamesProperties.getWords().getFilePrefix() + language.getLanguageCode() + ".txt"))) {

            String line;
            while((line = reader.readLine()) != null){
                words.add(line);
            }

        } catch (IOException exception){
            LOGGER.error(exception.getMessage());
        }

        return words;
    }

    public List<Word> generateRandomWords(Settings settings){
        List<String> tempWords = readWordsFile(settings.getLanguage());
        List<Word> resultWords = new ArrayList<>();

        AtomicInteger globalWordNumber = new AtomicInteger(1);

        BiConsumer<Integer, Color> wordsRandomizer = (wordsCount, color) -> {
            int tempWordIndex;
            for (int createdWordsCount = 0; createdWordsCount < wordsCount; createdWordsCount++){
                tempWordIndex = random.nextInt(tempWords.size() - 1);

                resultWords.add(new Word(globalWordNumber.getAndIncrement(), tempWords.get(tempWordIndex), color));

                tempWords.remove(tempWordIndex);
            }
        };

        int whiteWordsCount = settings.getWordsSettings().getWhiteWordsCount();
        int blackWordsCount = settings.getWordsSettings().getBlackWordsCount();
        int blueTeamWordsCount;
        int yellowTeamWordsCount;

        if (random.nextInt(100) > 50){
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
