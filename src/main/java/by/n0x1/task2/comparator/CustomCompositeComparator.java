package by.n0x1.task2.comparator;

import by.n0x1.task2.entity.TextComponent;

import java.util.Comparator;

public interface CustomCompositeComparator extends Comparator<TextComponent> {
    @Override
    int compare(TextComponent o1, TextComponent o2);
}
