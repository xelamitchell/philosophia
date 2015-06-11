package org.bugz.philosophia.user;

import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 *
 * @author bugz
 */
@Service
public class UserService {
    
    private final UserRepository repository;
    
    @Inject
    public UserService(UserRepository repository) {
        this.repository = repository;
    }
    
    public Boolean exists(String username) {
        return repository.exists(username);
    }
    
    public Boolean login(String username, String password) {
        User user = repository.findOne(username);
        return user.getPassword().equals(password);
    }
    
    public Boolean login(Credentials credentials) {
        return login(credentials.getUsername(), credentials.getPassword());
    }
    
}
