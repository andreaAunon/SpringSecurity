package es.neesis.security.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService {
    void createUser(UserDetails userDTO);
    UserDetails getUser(String username);
}
