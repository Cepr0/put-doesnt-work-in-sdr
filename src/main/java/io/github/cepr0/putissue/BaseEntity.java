package io.github.cepr0.putissue;

import lombok.EqualsAndHashCode;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;

/**
 * @author Cepro
 * @since 2017-08-09
 */
@MappedSuperclass
@EqualsAndHashCode
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity implements Identifiable<Integer> {
    
    @Id @GeneratedValue
    private final Integer id;
    
    protected BaseEntity() {
        this.id = null;
    }
    
    @Override
    public Integer getId() {
        return id;
    }
    
    @Override
    public String toString() {
        return "[id=" + id + "]";
    }
}
