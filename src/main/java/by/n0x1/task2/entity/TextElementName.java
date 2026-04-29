package by.n0x1.task2.entity;

public enum TextElementName {
    TEXT ("\n"),
    PARAGRAPH ("\n    "),
    SENTENCE (""),
    LEXEME (" "),
    LETTER (""),
    PUNCTUATION ("");
    TextElementName(String value){
        this.value = value;
    }
    private String value;

    public String getValue(){
        return value;
    }
}
