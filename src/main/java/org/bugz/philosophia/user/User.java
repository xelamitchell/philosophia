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
import org.bugz.philosophia.form.Pc;

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
    private List<Pc> forms;

    protected User() {}
    
    public User(String username, String password, List<Pc> forms) {
        this.username = username;
        this.password = password;
        this.forms = (CollectionUtils.isNotEmpty(forms)) ? forms : new ArrayList<>(0);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Pc> getForms() {
        return forms;
    }

    public void setForms(List<Pc> forms) {
        this.forms = forms;
    }
    
}
