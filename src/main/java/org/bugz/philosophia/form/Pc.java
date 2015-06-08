package org.bugz.philosophia.form;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.bugz.philosophia.form.Form;

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
    
    public Pc(String name, Integer knowledge, Integer reality, Integer willpower) {
        super(name, knowledge, reality, willpower);
    }
    
    public Pc(String name) {
        this(name, 0, 0, 0);
    }
    
}
