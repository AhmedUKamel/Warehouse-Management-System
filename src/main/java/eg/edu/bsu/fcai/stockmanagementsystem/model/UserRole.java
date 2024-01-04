package eg.edu.bsu.fcai.stockmanagementsystem.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;


public enum UserRole {
    CONSUMER, USER, ADMIN, SUPER_ADMIN;

    public SimpleGrantedAuthority getGrantedAuthorities() {
        return new SimpleGrantedAuthority(this.name());
    }
}
