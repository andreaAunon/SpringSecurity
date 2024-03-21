package es.neesis.security.dto;

import es.neesis.security.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.HashSet;

public class UserDetails {
    private String password;
    private String username;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetails(){

    }

    public UserDetails(User user){
        this.setUsername((user.getUsername()));
        this.setPassword((user.getPassword()));
        this.authorities = new HashSet<GrantedAuthority>(user.getAuthorities().size());
        user.getAuthorities().forEach(authority ->
                this.authorities.add(new SimpleGrantedAuthority("ROLE_") + authority.getAuthority()));
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
