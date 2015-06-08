package org.bugz.philosophia.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.collections4.CollectionUtils;
import org.bugz.philosophia.character.Pc;

/**
 * A MUD user.
 * 
 * @author bugz
 */
@Entity
@Table(name = "user")
public class User implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column
    private String username;
    
    @Column
    private String password;
    
    @Transient
    private List<Pc> pcs;

    protected User() {}
    
    public User(String username, String password, List<Pc> pcs) {
        this.username = username;
        this.password = password;
        this.pcs = (CollectionUtils.isNotEmpty(pcs)) ? pcs : new ArrayList<>(0);
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Pc> getPcs() {
        return pcs;
    }

    public void setPcs(List<Pc> pcs) {
        this.pcs = pcs;
    }
    
}
