package org.bugz.philosophia.form;

import javax.persistence.Entity;
import javax.persistence.Table;

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
    
    public Npc(String name, String description, Integer iteration,
            Integer knowledge, Integer reality, Integer willpower) {
        super(name, description, iteration, knowledge, reality, willpower);
    }
    
    public Npc(String name) {
        this(name, "", 4, 0, 0, 0);
    }
    
}
