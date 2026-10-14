/** Our own exception, thrown when a withdrawal is not allowed. */
public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
