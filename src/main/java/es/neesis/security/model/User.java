package es.neesis.security.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name="users")
public class User {
    @Id
    private String username;
    private String password;
    @OneToMany
    private Set<Authority> authorities;
}
