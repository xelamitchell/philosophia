package org.bugz.philosophia.user;

/*
 * (non-javadoc)
 * 
 * TODO
 * Make credentials embeddable and use this class within user.
 */
/**
 * A user's credentials.
 * 
 * @author bugz
 */
public class Credentials {
    
    private final String username;
    private final String password;
    
    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
}
