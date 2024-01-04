package eg.edu.bsu.fcai.stockmanagementsystem.service.entities;

import eg.edu.bsu.fcai.stockmanagementsystem.model.ApplicationUser;
import eg.edu.bsu.fcai.stockmanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static eg.edu.bsu.fcai.stockmanagementsystem.assets.ExceptionsMessagesRepository.*;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository repository;

    public ApplicationUser findById(String id) {
        return repository.findById(id).orElseThrow(() -> new UsernameNotFoundException(String.format(ID_NOT_FOUND, id)));
    }

    public ApplicationUser findByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(EMAIL_NOT_FOUND, email)));
    }

    public ApplicationUser add(ApplicationUser applicationUser) {
        return repository.save(applicationUser);
    }

    public List<ApplicationUser> findAll() {
        return repository.findAll();
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public long count() {
        return repository.count();
    }

    public List<ApplicationUser> findAllDisabledUsers() {
        return repository.findAllByIsEnabled(false);
    }

    public List<ApplicationUser> findAllEnabledUsers() {
        return repository.findAllByIsEnabled(true);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return this.findByEmail(username);
    }
}
