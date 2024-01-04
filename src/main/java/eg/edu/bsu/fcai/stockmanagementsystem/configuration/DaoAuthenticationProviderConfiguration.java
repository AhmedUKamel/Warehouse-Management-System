package eg.edu.bsu.fcai.stockmanagementsystem.configuration;

import eg.edu.bsu.fcai.stockmanagementsystem.service.entities.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration@RequiredArgsConstructor
public class DaoAuthenticationProviderConfiguration {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    @Bean public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }
}
