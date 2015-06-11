package org.bugz.philosophia.form;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author bugz
 */
@MappedSuperclass
public abstract class Form implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column
    private String name;
    
    @Column
    private String description;
    
    @Column
    private Integer iteration;
    
    @Column
    private Integer knowledge;
    
    @Column
    private Integer reality;
    
    @Column
    private Integer willpower;
    
    protected Form() {}
    
    public Form(String name, String description, Integer iteration,
            Integer knowledge, Integer reality, Integer willpower) {
        this.name = name;
        this.description = description;
        this.iteration = iteration;
        this.knowledge = knowledge;
        this.reality = reality;
        this.willpower = willpower;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIteration() {
        return iteration;
    }

    public void setIteration(Integer iteration) {
        this.iteration = iteration;
    }

    public Integer getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(Integer knowledge) {
        this.knowledge = knowledge;
    }

    public Integer getReality() {
        return reality;
    }

    public void setReality(Integer reality) {
        this.reality = reality;
    }

    public Integer getWillpower() {
        return willpower;
    }

    public void setWillpower(Integer willpower) {
        this.willpower = willpower;
    }
    
    /**
     * Forms evolve by iterating into polyhedrons with a larger number of sides.
     * Each Iteration represents one of <a href="http://en.wikipedia.org/wiki/Platonic_solid">Platonic Solids</a>.
     * 
     * @author bugz
     */
    public enum Iteration {
        
        TETRAHEDRON(4),
        CUBE(6),
        OCTAHEDRON(8),
        DODECAHERON(12),
        ICOSAHEDRON(20),
        SPHERE(1);
        
        private final Integer sides;
        
        private Iteration(Integer sides) {
            this.sides = sides;
        }

        public Integer getSides() {
            return sides;
        }
        
        public static Iteration valueOf(Integer sides) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
    }
    
}
