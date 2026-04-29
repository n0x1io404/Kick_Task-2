package by.n0x1.task2.entity;

import java.util.List;

public class Letter implements TextComponent {
    private TextElementName elementName;
    private String symbolValue;
    public Letter(String symbolValue){
        this.symbolValue = symbolValue;
        elementName = TextElementName.LETTER;
    }
    @Override
    public String conversionOperation() {
        return getValue();
    }
    @Override
    public boolean addChild(TextComponent textComponent){
        throw new UnsupportedOperationException("Can not invoke this method for leaf component.");
    }

    @Override
    public boolean removeChild(TextComponent textComponent){
        throw new UnsupportedOperationException("Can not invoke this method for leaf component.");
    }

    private String getValue() {
        return symbolValue;
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
