package by.n0x1.task2.parser;

import by.n0x1.task2.entity.TextComponent;

import java.util.List;

public interface CustomHandler {
    List<TextComponent> handleRequest(String source);
}

