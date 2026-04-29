package by.n0x1.task2.entity;

import java.util.List;

public class Punctuation implements TextComponent {
    private TextElementName elementName;
    private String symbolValue;
    public Punctuation(String symbolValue){
        this.symbolValue = symbolValue;
        elementName = TextElementName.PUNCTUATION;
    }
    @Override
    public String conversionOperation() {
        return getValue();
    }
    private String getValue() {
        return symbolValue;
    }

    @Override
    public boolean addChild(TextComponent textComponent){
        throw new UnsupportedOperationException("Can not invoke this method for leaf component.");
    }

    @Override
    public boolean removeChild(TextComponent textComponent){
        throw new UnsupportedOperationException("Can not invoke this method for leaf component.");
    }

    @Override
    public TextElementName getElementName() {
        return elementName;
    }

    @Override
    public List<TextComponent> getTopChildren(){
        throw new UnsupportedOperationException("Can not invoke this method for leaf component.");
    }
}
