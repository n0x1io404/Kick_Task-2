package by.n0x1.task2.parser.impl;

import by.n0x1.task2.entity.TextComponent;
import by.n0x1.task2.entity.TextComposite;
import by.n0x1.task2.entity.TextElementName;
import by.n0x1.task2.parser.CustomHandler;

import java.util.ArrayList;
import java.util.List;


public class TextHandler implements CustomHandler {
    private CustomHandler successor = new ParagraphHandler();
    @Override
    public List<TextComponent> handleRequest(String source){
        List<TextComponent> texts = new ArrayList<>();
        texts.add(new TextComposite(TextElementName.TEXT));
        for(TextComponent paragraph : successor.handleRequest(source)){
            texts.get(0).addChild(paragraph);
        }
        return texts;
    }
}
