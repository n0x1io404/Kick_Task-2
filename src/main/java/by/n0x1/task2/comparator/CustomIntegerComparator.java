package by.n0x1.task2.comparator;

import java.util.Comparator;

public interface CustomIntegerComparator extends Comparator<Integer> {
    @Override
    int compare(Integer o1, Integer o2);
}
