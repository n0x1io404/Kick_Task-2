package by.n0x1.task2.service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextService {

    public int findMaxSentencesWithSameWords(List<String> sentences) {
        Map<String, Integer> wordToSentenceCount = new HashMap<>();

        for (String sentence : sentences) {
            Set<String> uniqueWordsInSentence = Arrays.stream(sentence.toLowerCase().split("\\P{L}+"))
                    .filter(w -> !w.isEmpty())
                    .collect(Collectors.toSet());

            for (String word : uniqueWordsInSentence) {
                wordToSentenceCount.put(word, wordToSentenceCount.getOrDefault(word, 0) + 1);
            }
        }

        return wordToSentenceCount.values().stream()
                .max(Integer::compareTo)
                .orElse(0);
    }

    public List<String> sortSentencesByCharCount(List<String> sentences, char targetChar) {
        char lowerTarget = Character.toLowerCase(targetChar);

        return sentences.stream()
                .sorted(Comparator.comparingLong(sentence ->
                        sentence.toLowerCase().chars().filter(c -> c == lowerTarget).count()
                ))
                .collect(Collectors.toList());
    }

    public List<String> swapFirstAndLastLexemes(List<String> sentences) {
        List<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile("^(\\S+)(.*\\s)(\\S+)\\s*$");

        for (String sentence : sentences) {
            Matcher matcher = pattern.matcher(sentence);
            if (matcher.find()) {
                String swapped = matcher.group(3) + matcher.group(2) + matcher.group(1);
                result.add(swapped);
            } else {
                result.add(sentence);
            }
        }
        return result;
    }
}