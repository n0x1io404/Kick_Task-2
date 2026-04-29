package by.n0x1.task2.reader;

import by.n0x1.task2.exception.InteractionException;

public interface CustomReader {
    String readDataFromFile(String filePath) throws InteractionException;
}
