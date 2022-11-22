package exceptions;

public class PokerHandEvaluationException extends RuntimeException {

    private final String message;

    public PokerHandEvaluationException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "- " + message;
    }
}
