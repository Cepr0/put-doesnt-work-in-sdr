package io.github.cepr0.putissue;

import org.springframework.hateoas.Identifiable;

import javax.persistence.*;

/**
 * @author Cepro
 * @since 2017-08-09
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BaseEntity implements Identifiable<Integer> {
    
    @Id @GeneratedValue
    private final Integer id;
    
    public BaseEntity() {
        this.id = null;
    }
    
    @Override
    public Integer getId() {
        return id;
    }
    
    @Override
    public String toString() {
        return "id=" + id;
    }
}