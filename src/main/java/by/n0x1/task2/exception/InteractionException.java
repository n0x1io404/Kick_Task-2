package by.n0x1.task2.exception;

public class InteractionException extends Exception{
    public InteractionException() {
    }
    public InteractionException(String message) {
        super(message);
    }

    public InteractionException(String message, Throwable cause) {
        super(message, cause);
    }

    public InteractionException(Throwable cause) {
        super(cause);
    }
}
