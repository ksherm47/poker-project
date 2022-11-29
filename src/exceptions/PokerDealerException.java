package exceptions;

public class PokerDealerException extends RuntimeException {

    private final String message;

    public PokerDealerException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "- " + message;
    }
}
