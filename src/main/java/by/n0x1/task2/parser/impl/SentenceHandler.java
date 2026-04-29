package by.n0x1.task2.parser.impl;

import by.n0x1.task2.entity.TextComponent;
import by.n0x1.task2.entity.TextComposite;
import by.n0x1.task2.entity.TextElementName;
import by.n0x1.task2.parser.CustomHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SentenceHandler implements CustomHandler {
    private CustomHandler successor = new LexemeHandler();
    private final static String SENTENCES_DELIMITER_PATTERN = "(?<=[.!?])\\s* ";
    private final static String NEW_LINE_PATTERN = "\n";
    @Override
    public List<TextComponent> handleRequest(String source){
        List<TextComponent> sentences = new ArrayList<>();
        List<String> sentencesInString = Arrays.asList(source.split(SENTENCES_DELIMITER_PATTERN).clone());
        int sentencesLoopCounter = 0;
        for(String sentence : sentencesInString){
            if(!sentence.equals(NEW_LINE_PATTERN)){
                sentences.add(new TextComposite(TextElementName.SENTENCE));
                for(TextComponent lexeme : successor.handleRequest(sentence)){
                    sentences.get(sentencesLoopCounter).addChild(lexeme);
                }
                sentencesLoopCounter++;
            }
        }
        return sentences;
    }
}
