package eg.edu.bsu.fcai.stockmanagementsystem.exception;

public class ExistUserDetailsException extends RuntimeException {
    public ExistUserDetailsException(String message) {
        super(message);
    }
}
