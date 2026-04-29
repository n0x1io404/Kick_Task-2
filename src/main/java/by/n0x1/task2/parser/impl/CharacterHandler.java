package by.n0x1.task2.parser.impl;

import by.n0x1.task2.entity.TextComponent;
import by.n0x1.task2.entity.Letter;
import by.n0x1.task2.entity.Punctuation;
import by.n0x1.task2.parser.CustomHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CharacterHandler implements CustomHandler {
    private final static String CHARACTERS_DELIMITER_PATTERN = "";
    private final static String PUNCTUATION_PATTERN ="\\p{Punct}";
    @Override
    public List<TextComponent> handleRequest(String source) {
        List<TextComponent> characters = new ArrayList<>();
        List<String> charactersInString = Arrays.asList(source.split(CHARACTERS_DELIMITER_PATTERN).clone());
        for(String character : charactersInString){
            if(Pattern.matches(PUNCTUATION_PATTERN,character)){
                characters.add(new Punctuation(character));
            }else {
                characters.add(new Letter(character));
            }
        }
        return characters;
    }
}
