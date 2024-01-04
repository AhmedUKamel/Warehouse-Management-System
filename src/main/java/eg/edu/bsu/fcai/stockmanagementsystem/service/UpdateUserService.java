package eg.edu.bsu.fcai.stockmanagementsystem.service;

import eg.edu.bsu.fcai.stockmanagementsystem.assets.UserForm;
import eg.edu.bsu.fcai.stockmanagementsystem.assets.Validator;
import eg.edu.bsu.fcai.stockmanagementsystem.exception.ExistUserDetailsException;
import eg.edu.bsu.fcai.stockmanagementsystem.exception.InvalidUserDetailsException;
import eg.edu.bsu.fcai.stockmanagementsystem.exception.PasswordMatchingException;
import eg.edu.bsu.fcai.stockmanagementsystem.model.ApplicationUser;
import eg.edu.bsu.fcai.stockmanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static eg.edu.bsu.fcai.stockmanagementsystem.assets.ExceptionsMessagesRepository.*;

@Service
@RequiredArgsConstructor
public class UpdateUserService {
    private final UserRepository repository;
    private final Validator validator;
    private final PasswordEncoder passwordEncoder;

    public void updateUser(UserForm form, boolean isCurrentUser) {
        updateArabicName(form.nameArabic().strip(), form.id(), isCurrentUser);
        updateEnglishName(form.nameEnglish().strip(), form.id(), isCurrentUser);
        updateEmail(form.email().strip().toLowerCase(), form.id(), isCurrentUser);
        updatePhone(form.phone().strip(), form.id(), isCurrentUser);
        updateImage(form.imageUrl().strip(), form.id(), isCurrentUser);
        updatePassword(form.password(), form.confirmPassword(), form.id(), isCurrentUser);
    }

    private void updatePassword(String password, String confirmPassword, String id, boolean isCurrentUser) {
        repository.findById(id).ifPresentOrElse(user -> {
            if (!password.equals(confirmPassword) && !password.isBlank()) {
                throw new PasswordMatchingException(String.format(PASSWORD_DOES_NOT_MATCH, password, confirmPassword));
            }
            if (validator.isWeakPassword(password) && !password.isBlank()) {
                throw new InvalidUserDetailsException(String.format(WEAK_PASSWORD, password));
            }
            if (!passwordEncoder.matches(password, user.getPassword()) && !password.isBlank()) {
                user.setPassword(passwordEncoder.encode(password));
                repository.save(user);
                if (isCurrentUser) {
                    updateSecurityContextHolder(user);
                }
            }
        }, () -> {
            throw new UsernameNotFoundException(String.format(ID_NOT_FOUND, id));
        });
    }

    private void updateArabicName(String arabicName, String id, boolean isCurrentUser) {
        repository.findById(id).ifPresentOrElse(user -> {
            if (!user.getNameArabic().equals(arabicName)) {
                user.setNameArabic(arabicName);
                repository.save(user);
                if (isCurrentUser) {
                    updateSecurityContextHolder(user);
                }
            }
        }, () -> {
            throw new UsernameNotFoundException(String.format(ID_NOT_FOUND, id));
        });
    }

    private void updateEnglishName(String englishName, String id, boolean isCurrentUser) {
        repository.findById(id).ifPresentOrElse(user -> {
            if (!user.getNameEnglish().equals(englishName)) {
                user.setNameEnglish(englishName);
                repository.save(user);
                if (isCurrentUser) {
                    updateSecurityContextHolder(user);
                }
            }
        }, () -> {
            throw new UsernameNotFoundException(String.format(ID_NOT_FOUND, id));
        });
    }

    private void updateEmail(String email, String id, boolean isCurrentUser) {
        repository.findById(id).ifPresentOrElse(user -> {
            if (validator.isInvalidEmail(user.getEmail())) {
                throw new InvalidUserDetailsException(String.format(INVALID_EMAIL, email));
            }
            final Optional<ApplicationUser> optionalUser = repository.findByEmail(email);
            if (optionalUser.isPresent() && !optionalUser.get().equals(user)) {
                throw new ExistUserDetailsException(String.format(EXIST_EMAIL, email));
            }
            if (!user.getEmail().equals(email)) {
                user.setEmail(email);
                repository.save(user);
                if (isCurrentUser) {
                    updateSecurityContextHolder(user);
                }
            }
        }, () -> {
            throw new UsernameNotFoundException(String.format(ID_NOT_FOUND, id));
        });
    }

    private void updatePhone(String phone, String id, boolean isCurrentUser) {
        repository.findById(id).ifPresentOrElse(user -> {
            if (validator.isIncorrectPhone(phone)) {
                throw new InvalidUserDetailsException(String.format(INVALID_PHONE, phone));
            }
            final Optional<ApplicationUser> optionalUser = repository.findByPhone(phone);
            if (optionalUser.isPresent() && !optionalUser.get().equals(user)) {
                throw new ExistUserDetailsException(String.format(EXIST_PHONE, phone));
            }
            if (!user.getPhone().equals(phone)) {
                user.setPhone(phone);
                repository.save(user);
                if (isCurrentUser) {
                    updateSecurityContextHolder(user);
                }
            }
        }, () -> {
            throw new UsernameNotFoundException(String.format(ID_NOT_FOUND, id));
        });
    }

    private void updateImage(String imageUrl, String id, boolean isCurrentUser) {
        repository.findById(id).ifPresentOrElse(user -> {
            user.setImageUrl(imageUrl);
            repository.save(user);
            if (isCurrentUser) {
                updateSecurityContextHolder(user);
            }
        }, () -> {
            throw new UsernameNotFoundException(String.format(ID_NOT_FOUND, id));
        });
    }

    private void updateSecurityContextHolder(ApplicationUser applicationUser) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Authentication newAuthentication = new UsernamePasswordAuthenticationToken(applicationUser, authentication.getCredentials(), authentication.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuthentication);
    }
}
