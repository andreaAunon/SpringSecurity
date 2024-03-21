package es.neesis.security.services;

import es.neesis.security.model.User;
import es.neesis.security.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void init(){
        UserDetails user1 = new UserDetails();
        user1.setUsername("a");
        user1.setPassword("aaa");
        user1.setRoles("USER");

        UserDetails user2 = new UserDetails();
        user2.setUsername("b");
        user2.setPassword("bbb");
        user2.setRoles("ADMIN");

        createUser(user1);
        createUser(user2);
    }

    @Override
    public UserDetails getUser(String username) {
        Optional<User> user = userRepository.findById(username);

        if(user.isPresent()){
            return userToUserDTO(user.get());
        } else {
            // Aqui irian las comprobaciones de que pasa si el usuario no existe
            return null;
        }
    }

    private UserDetails userToUserDetails(User user){
        UserDetails userDTO = new UserDetails(user);
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setRoles(user.getRoles());

        return userDTO;
    }

    public UserDetailsService userDetailsService(User user) {
        UserDetails userDetails = org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();
        return new InMemoryUserDetailsManager(userDetails);
    }
}
