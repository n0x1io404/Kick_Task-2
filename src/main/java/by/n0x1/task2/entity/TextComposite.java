package by.n0x1.task2.entity;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {
    private TextElementName elementName;
    private List<TextComponent> components;
    public TextComposite(TextElementName elementName){
        components = new ArrayList<>();
        this.elementName = elementName;
    }
    @Override
    public String conversionOperation() {
        String text ="";
        for (TextComponent component : components){
            if(component.getElementName() == TextElementName.PARAGRAPH){
                text = text.concat(component.getElementName().getValue() + component.conversionOperation() );
            }else {
                text = text.concat(component.conversionOperation() + component.getElementName().getValue());
            }
        }
        return text;
    }

    @Override
    public boolean addChild(TextComponent textComponent) {
        return components.add(textComponent);
    }

    @Override
    public boolean removeChild(TextComponent textComponent) {
        return components.remove(textComponent);
    }

    @Override
    public TextElementName getElementName() {
        return elementName;
    }

    @Override
    public List<TextComponent> getTopChildren() {
        return components;
    }
}
