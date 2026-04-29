package by.n0x1.task2.entity;

import java.util.List;

public interface TextComponent {
    String conversionOperation();
    boolean addChild(TextComponent textComponent);
    boolean removeChild(TextComponent textComponent);
    TextElementName getElementName();
    List<TextComponent> getTopChildren();
}
