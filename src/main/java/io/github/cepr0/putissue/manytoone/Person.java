package io.github.cepr0.putissue.manytoone;

import io.github.cepr0.putissue.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import static lombok.AccessLevel.PROTECTED;

/**
 * @author Cepro
 * @since 2017-08-09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class Person extends BaseEntity {
    
    private String name;
    
    @ManyToOne
    private Address address;
}
