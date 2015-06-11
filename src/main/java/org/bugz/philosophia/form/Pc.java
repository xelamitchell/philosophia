package org.bugz.philosophia.form;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * A user's Form.
 * 
 * @author bugz
 */
@Entity
@Table(name = "pc")
public class Pc extends Form {
    
    private static final long serialVersionUID = 1L;
    
    protected Pc() {
        super();
    }
    
    public Pc(String name, String description, Integer iteration, Integer knowledge, Integer reality, Integer willpower) {
        super(name, description, iteration, knowledge, reality, willpower);
    }
    
    public Pc(String name) {
        this(name, "", 4, 0, 0, 0);
    }
    
}
