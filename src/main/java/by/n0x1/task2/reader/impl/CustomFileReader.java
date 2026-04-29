package by.n0x1.task2.reader.impl;

import by.n0x1.task2.exception.InteractionException;
import by.n0x1.task2.reader.CustomReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomFileReader implements CustomReader {
    private static CustomFileReader instance;
    private CustomFileReader(){};
    public static CustomFileReader getInstance() {
        if(instance == null){
            instance = new CustomFileReader();
        }
        return instance;
    }
    @Override
    public String readDataFromFile(String filePath) throws InteractionException {
        String dataFromFile = "";
        List<String> dataList = new ArrayList<>();
        try(FileReader fileReader = new FileReader(filePath); BufferedReader bufferedReader = new BufferedReader(fileReader)){
            dataList = bufferedReader.lines().collect(Collectors.toList());
            for(String o : dataList){
                if(o.contains("    ")){
                    dataFromFile = dataFromFile.concat("\n" + o +" ");
                }else {
                    dataFromFile = dataFromFile.concat(o +" ");
                }
            }
        } catch (IOException e) {
            throw new InteractionException(e.getMessage());
        }
        return dataFromFile;
    }
}
