package eg.edu.bsu.fcai.stockmanagementsystem.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.concurrent.TimeUnit;

import static eg.edu.bsu.fcai.stockmanagementsystem.assets.Key.KEY;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityFilterChainConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/assets/**", "/bootstrap/**", "/plugins/**", "/register/**", "/registered/**", "/init").permitAll()
                                .anyRequest().authenticated())
                .formLogin(form ->
                        form.loginPage("/login").permitAll()
                                .defaultSuccessUrl("/", true)
                                .usernameParameter("username")
                                .passwordParameter("password"))
                .rememberMe(remember -> remember.tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(15))
                        .key(KEY)
                        .rememberMeParameter("remember-me")).
                logout(logout ->
                        logout.logoutUrl("/logout")
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                                .clearAuthentication(true)
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID", "remember-me")
                                .logoutSuccessUrl("/login"));
        return http.build();
    }
}
