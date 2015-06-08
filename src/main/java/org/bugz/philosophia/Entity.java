package org.bugz.philosophia;

import java.io.Serializable;

/**
 * A Quill entity (the base for all game-specific objects).
 * 
 * @author bugz
 */
public abstract class Entity implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String name;
    
    private String description;
    
}
