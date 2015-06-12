package org.bugz.philosophia.user;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * A user's credentials.
 * 
 * @author bugz
 */
@Embeddable
public class Credentials implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Column
    private String username;
    
    @Column
    private String password;
    
    protected Credentials() {}
    
    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
