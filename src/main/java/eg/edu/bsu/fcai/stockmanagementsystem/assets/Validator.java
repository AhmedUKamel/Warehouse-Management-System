package eg.edu.bsu.fcai.stockmanagementsystem.assets;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class Validator {
    private static final String ID_REGEX = "[0-9]{14}";
    private static final String PHONE_REGEX = "01[0-9]{9}";
    private static final String EMAIL_REGEX = "^(.+)@(.+)$";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$";


    public boolean isInvalidEmail(String email) {
        return !Pattern.compile(EMAIL_REGEX).matcher(email).matches();
    }

    public boolean isWeakPassword(String password) {
//        return !Pattern.compile(PASSWORD_REGEX).matcher(password).matches();
        return false;
    }

    public boolean isIncorrectId(String id) {
        return !Pattern.compile(ID_REGEX).matcher(id).matches();
    }

    public boolean isIncorrectPhone(String phone) {
        return !Pattern.compile(PHONE_REGEX).matcher(phone).matches();
    }
}
