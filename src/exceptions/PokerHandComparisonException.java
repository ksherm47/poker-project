package exceptions;

public class PokerHandComparisonException extends RuntimeException {

    private final String message;

    public PokerHandComparisonException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "- " + message;
    }
}
