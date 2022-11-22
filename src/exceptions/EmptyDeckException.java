package exceptions;

public class EmptyDeckException extends RuntimeException {

    private final String message;

    public EmptyDeckException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "- " + message;
    }
}
