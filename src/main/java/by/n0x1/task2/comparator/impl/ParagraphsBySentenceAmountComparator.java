package by.n0x1.task2.comparator.impl;

import by.n0x1.task2.entity.TextComponent;
import by.n0x1.task2.comparator.CustomCompositeComparator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParagraphsBySentenceAmountComparator implements CustomCompositeComparator {
    private static Logger logger = LogManager.getLogger();
    @Override
    public int compare(TextComponent o1, TextComponent o2) {
        if(o1.getTopChildren().size() > o2.getTopChildren().size()){
            return 1;
        }else if(o1.getTopChildren().size() < o2.getTopChildren().size()){
            return -1;
        }else {
            return  0;
        }
    }
}
