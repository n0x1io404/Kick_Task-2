package by.n0x1.task2.service.impl;

import by.n0x1.task2.entity.TextComponent;
import by.n0x1.task2.entity.TextComposite;
import by.n0x1.task2.entity.TextElementName;
import by.n0x1.task2.exception.InteractionException;
import by.n0x1.task2.service.CustomCompositeService;
import by.n0x1.task2.comparator.impl.IndexesComparator;
import by.n0x1.task2.comparator.impl.LexemesByWordSizeComparator;
import by.n0x1.task2.comparator.impl.ParagraphsBySentenceAmountComparator;

import java.util.*;

public class TextCompositeService implements CustomCompositeService {
    private final static String VOWELS_PATTERN = "[aeiouAEIOUаеёиоуюяАЕЁИОУЕЮЯэЭыЫ]";
    private static TextCompositeService instance;
    private TextCompositeService(){}
    public static TextCompositeService getInstance() {
        if(instance == null){
            instance = new TextCompositeService();
        }
        return instance;
    }
    public void sortParagraphsInTextCompositeBySentenceAmount(TextComponent text) throws InteractionException {
        TextComponent sortedText = new TextComposite(TextElementName.TEXT);
        if(text.getElementName()!= TextElementName.TEXT){
            throw new InteractionException("Invalid text element was passed into method.");
        }else {
            List<TextComponent> paragraphs = text.getTopChildren();
            paragraphs.sort(new ParagraphsBySentenceAmountComparator());
            for(TextComponent o : paragraphs){
                sortedText.addChild(o);
            }
        }
        text = sortedText;
    }
    public TextComponent findSentenceWithTheLongestWord(TextComponent text) throws InteractionException{
        TextComponent sentence = new TextComposite(TextElementName.SENTENCE);
        if(text.getElementName()!= TextElementName.TEXT){
            throw new InteractionException("Invalid text element was passed into method.");
        }else {
            Map<Integer, TextComponent> sentenceAndBiggestWordSizeInText = new HashMap<>();
            List<TextComponent> paragraphs = text.getTopChildren();
            for(TextComponent o : paragraphs){
                List<TextComponent> initialSentences = o.getTopChildren();
                Map<Integer, TextComponent> sentenceAndBiggestWordSizeInParagraph = new HashMap<>();
                for(TextComponent a : initialSentences){
                    List<TextComponent> initialLexemes = a.getTopChildren();
                    TextComponent biggestWord = initialLexemes.stream().max(new LexemesByWordSizeComparator()).get();
                    sentenceAndBiggestWordSizeInParagraph.put(getLettersAmountInLexeme(biggestWord),a);
                }
                int biggestLettersNumberInParagraph = sentenceAndBiggestWordSizeInParagraph.keySet().stream().max(new IndexesComparator()).get();
                sentenceAndBiggestWordSizeInText.put(biggestLettersNumberInParagraph,
                        sentenceAndBiggestWordSizeInParagraph.get(biggestLettersNumberInParagraph));
            }
            int biggestLettersNumberInText =sentenceAndBiggestWordSizeInText.keySet().stream().max(new IndexesComparator()).get();
            sentence = sentenceAndBiggestWordSizeInText.get(biggestLettersNumberInText);
        }
        return sentence;

    }
    public void removeFromTextSentencesWithWordAmountLessThenWritten(Integer wordsAmount, TextComponent text) throws InteractionException {
       if(text.getElementName() != TextElementName.TEXT){
           throw new InteractionException("Invalid text element was passed into method.");
       }else {
           Map<Integer,Map<Integer,TextComponent>> sentencesToRemove = new HashMap<>();
           int paragraphNumber = 0;
           int outerMapCounter = 0;
           for(TextComponent o : text.getTopChildren()){
               for(TextComponent a : o.getTopChildren()){
                   int sentenceSize = a.getTopChildren().size();
                   if(sentenceSize < wordsAmount){
                       Map<Integer,TextComponent> innerMap = new HashMap<>();
                       innerMap.put(paragraphNumber,a);
                       sentencesToRemove.put(outerMapCounter,innerMap);
                       outerMapCounter++;
                   }
               }
               paragraphNumber++;
           }
           int tempCounter =0;
           while (tempCounter < outerMapCounter){
               for(Integer o : sentencesToRemove.get(tempCounter).keySet()){
                   text.getTopChildren().get(o).removeChild(sentencesToRemove.get(tempCounter).get(o));
               }
               tempCounter++;
           }
       }
    }
    public Map<TextComponent,Integer> findRepetitiveWordsAndTheirNumber(TextComponent text) throws InteractionException{
        Map<TextComponent,Integer> repetitiveWordsAndTheirNumberMap = new HashMap<>();
        if(text.getElementName() != TextElementName.TEXT){
            throw new InteractionException("Invalid text element was passed into method.");
        }else {
            for(TextComponent paragraph : text.getTopChildren()){
                for(TextComponent sentence : paragraph.getTopChildren()){
                    for(TextComponent lexeme : sentence.getTopChildren()){
                        boolean wordExists = false;
                        for(TextComponent existingComponent : repetitiveWordsAndTheirNumberMap.keySet()){
                            if(compareWordsInLexemes(existingComponent,lexeme) && !wordExists){
                                int oldAmount = repetitiveWordsAndTheirNumberMap.get(existingComponent);
                                repetitiveWordsAndTheirNumberMap.replace(existingComponent,++oldAmount);
                                wordExists =true;
                            }
                        }
                        if(!wordExists){
                            repetitiveWordsAndTheirNumberMap.put(lexeme,1);
                        }
                    }
                }
            }
        }
        return repetitiveWordsAndTheirNumberMap;
    }
    public String countNumberOfConsonantsAndVowelsInSentence(Integer paragraphNumber,Integer sentenceInParagraphNumber,TextComponent text)throws InteractionException{
        String result =null;
        if(text.getElementName() != TextElementName.TEXT){
            throw new InteractionException("Invalid text element was passed into method.");
        }else {
            if(paragraphNumber-1 >= 0 && paragraphNumber-1<text.getTopChildren().size()){
                if(sentenceInParagraphNumber-1 >= 0 && sentenceInParagraphNumber-1 < text.getTopChildren().get(paragraphNumber-1).getTopChildren().size()){
                    TextComponent sentence = text.getTopChildren().get(paragraphNumber-1).getTopChildren().get(sentenceInParagraphNumber-1);
                    int vowelsNumber =0;
                    int consonantsNumber =0;
                    for(TextComponent lexeme : sentence.getTopChildren()){
                        for(TextComponent character : lexeme.getTopChildren()){
                            if(character.getElementName() == TextElementName.LETTER){
                                if(character.conversionOperation().matches(VOWELS_PATTERN)){
                                    vowelsNumber++;
                                }else {
                                    consonantsNumber++;
                                }
                            }
                        }
                    }
                    return result = "vowels: " +vowelsNumber+ ", consonants: "+consonantsNumber+".";
                }else {
                    throw new InteractionException("Invalid sentence number.");
                }
            }else {
                throw new InteractionException("Invalid paragraph number.");
            }
        }
    }
    private boolean compareWordsInLexemes(TextComponent lexeme1, TextComponent lexeme2)throws InteractionException {
        if(lexeme1.getElementName() != TextElementName.LEXEME || lexeme2.getElementName() != TextElementName.LEXEME){
            throw new InteractionException("Invalid text element was passed into method.");
        }else {
            List<String> lettersInL1 = new ArrayList<>();
            List<String> lettersInL2 = new ArrayList<>();
            for(TextComponent characterInL1 : lexeme1.getTopChildren()){
                if(characterInL1.getElementName() == TextElementName.LETTER){
                    lettersInL1.add(characterInL1.conversionOperation());
                }
            }
            for(TextComponent characterInL2 : lexeme2.getTopChildren()){
                if(characterInL2.getElementName() == TextElementName.LETTER){
                    lettersInL2.add(characterInL2.conversionOperation());
                }
            }

            if(lettersInL1.size() != lettersInL2.size()){
                return false;
            }else {
                int letterCounterInListOfLetters=0;
                for(String letter : lettersInL1){
                    if(!letter.equals(lettersInL2.get(letterCounterInListOfLetters)) &&
                            !letter.toUpperCase().equals(lettersInL2.get(letterCounterInListOfLetters)) &&
                            !letter.toLowerCase().equals(lettersInL2.get(letterCounterInListOfLetters))){
                        return false;
                    }
                    letterCounterInListOfLetters++;
                }
            }
        }
        return true;
    }
    private Integer getLettersAmountInLexeme(TextComponent lexeme) throws InteractionException {
        if(lexeme.getElementName() != TextElementName.LEXEME){
            throw new InteractionException("Invalid text element was passed into method.");
        }else {
            int lettersCounter = 0;
            for(TextComponent o : lexeme.getTopChildren()){
                if(o.getElementName() == TextElementName.LETTER){
                    lettersCounter++;
                }
            }
            return lettersCounter;
        }
    }
}
