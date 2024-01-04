package eg.edu.bsu.fcai.stockmanagementsystem.exception;

public class InvalidUserDetailsException extends RuntimeException {
    public InvalidUserDetailsException(String message) {
        super(message);
    }
}
