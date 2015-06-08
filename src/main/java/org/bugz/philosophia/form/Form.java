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
    private Integer knowledge;
    
    @Column
    private Integer reality;
    
    @Column
    private Integer willpower;
    
    protected Form() {}
    
    public Form(String name, Integer knowledge, Integer reality, Integer willpower) {
        this.name = name;
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
    
}
