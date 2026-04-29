package by.n0x1.task2.comparator.impl;

import by.n0x1.task2.entity.TextComponent;
import by.n0x1.task2.entity.TextElementName;
import by.n0x1.task2.comparator.CustomCompositeComparator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class LexemesByWordSizeComparator implements CustomCompositeComparator {
    private static Logger logger = LogManager.getLogger();
    @Override
    public int compare(TextComponent o1, TextComponent o2) {
        List<TextComponent> lettersAndPunctuationInO1 = o1.getTopChildren();
        List<TextComponent> lettersAndPunctuationInO2 = o2.getTopChildren();
        int numberOfLettersInO1 = 0;
        int numberOfLettersInO2 = 0;
        for(TextComponent o : lettersAndPunctuationInO1){
            if(o.getElementName() == TextElementName.LETTER){
                numberOfLettersInO1++;
            }
        }
        for(TextComponent o : lettersAndPunctuationInO2){
            if(o.getElementName() == TextElementName.LETTER){
                numberOfLettersInO2++;
            }
        }
        if(numberOfLettersInO1 > numberOfLettersInO2){
            return 1;
        }else if(numberOfLettersInO1 < numberOfLettersInO2){
            return -1;
        }else {
            return  0;
        }
    }
}
