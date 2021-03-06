package ip.counter;

public class NoFileException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public NoFileException(String messages, Exception e) {
        super(messages + e);
    }

    public NoFileException(String messages) {
        super(messages);
    }
}