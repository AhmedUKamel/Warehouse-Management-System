package eg.edu.bsu.fcai.stockmanagementsystem.exception;

public class PasswordMatchingException extends RuntimeException {
    public PasswordMatchingException(String message) {
        super(message);
    }
}
