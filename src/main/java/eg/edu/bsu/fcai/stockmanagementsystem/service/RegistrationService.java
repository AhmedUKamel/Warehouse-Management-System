package eg.edu.bsu.fcai.stockmanagementsystem.service;

import eg.edu.bsu.fcai.stockmanagementsystem.assets.UserForm;
import eg.edu.bsu.fcai.stockmanagementsystem.assets.Validator;
import eg.edu.bsu.fcai.stockmanagementsystem.exception.ExistUserDetailsException;
import eg.edu.bsu.fcai.stockmanagementsystem.exception.InvalidUserDetailsException;
import eg.edu.bsu.fcai.stockmanagementsystem.model.ApplicationUser;
import eg.edu.bsu.fcai.stockmanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static eg.edu.bsu.fcai.stockmanagementsystem.assets.ExceptionsMessagesRepository.*;
import static eg.edu.bsu.fcai.stockmanagementsystem.model.UserRole.CONSUMER;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository repository;
    private final Validator validator;
    private final PasswordEncoder passwordEncoder;

    public void register(UserForm form) {
        final String id = form.id().strip();
        checkId(id);

        final String email = form.email().strip().toLowerCase();
        checkEmail(email);

        final String phone = form.phone().strip();
        checkPhone(phone);

        final String password = form.password();
        final String confirmedPassword = form.confirmPassword();
        checkPassword(password, confirmedPassword);

        repository.save(toUser(form, id, email, phone, password));
    }

    private ApplicationUser toUser(UserForm form, String id, String email, String phone, String password) {
        return ApplicationUser.builder().id(id).email(email).phone(phone).password(passwordEncoder.encode(password)).imageUrl("/assets/img/profile.jpg").nameArabic(form.nameArabic()).nameEnglish(form.nameEnglish()).isEnabled(false).isAccountNonLocked(true).isAccountNonExpired(true).isCredentialsNonExpired(true).authorities(CONSUMER.getGrantedAuthorities()).build();
    }

    private void checkPassword(String password, String confirmedPassword) {
        if (!password.equals(confirmedPassword)) {
            throw new InvalidUserDetailsException(String.format(PASSWORD_DOES_NOT_MATCH, password, confirmedPassword));
        } else if (validator.isWeakPassword(password)) {
            throw new InvalidUserDetailsException(String.format(WEAK_PASSWORD, password));
        }
    }

    private void checkPhone(String phone) {
        if (validator.isIncorrectPhone(phone)) {
            throw new InvalidUserDetailsException(String.format(INVALID_PHONE, phone));
        } else if (repository.existsByPhone(phone)) {
            throw new ExistUserDetailsException(String.format(EXIST_PHONE, phone));
        }
    }

    private void checkEmail(String email) {
        if (validator.isInvalidEmail(email)) {
            throw new InvalidUserDetailsException(String.format(INVALID_EMAIL, email));
        } else if (repository.existsByEmail(email)) {
            throw new ExistUserDetailsException(String.format(EXIST_EMAIL, email));
        }
    }

    private void checkId(String id) {
        if (validator.isIncorrectId(id)) {
            throw new InvalidUserDetailsException(String.format(INVALID_ID, id));
        } else if (repository.existsById(id)) {
            throw new ExistUserDetailsException(String.format(EXIST_ID, id));
        }
    }
}
