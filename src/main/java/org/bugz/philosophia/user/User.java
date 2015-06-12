package org.bugz.philosophia.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Embedded;
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
    
    @Embedded
    private Credentials credentials;
    
    @Transient
    private List<Pc> forms;

    protected User() {}
    
    public User(Credentials credentials, List<Pc> forms) {
        this.credentials = credentials;
        this.forms = (CollectionUtils.isNotEmpty(forms)) ? forms : new ArrayList<>(0);
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public String getUsername() {
        return credentials.getUsername();
    }

    public String getPassword() {
        return credentials.getPassword();
    }

    public List<Pc> getForms() {
        return forms;
    }

    public void setForms(List<Pc> forms) {
        this.forms = forms;
    }
    
}
