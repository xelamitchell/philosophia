package org.bugz.philosophia.form;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.bugz.philosophia.form.Form;

/**
 * A Non-Player Character.
 * 
 * @author bugz
 */
@Entity
@Table(name = "npc")
public class Npc extends Form {
    
    private static final long serialVersionUID = 1L;
    
    protected Npc() {}
    
    public Npc(String name, Integer knowledge, Integer reality, Integer willpower) {
        super(name, knowledge, reality, willpower);
    }
    
    public Npc(String name) {
        this(name, 0, 0, 0);
    }
    
}
