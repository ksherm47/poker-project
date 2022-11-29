package exceptions;

public class DeckSizeException extends RuntimeException {

    private final String message;

    public DeckSizeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "- " + message;
    }
}
