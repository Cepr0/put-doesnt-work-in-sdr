package io.github.cepr0.putissue.manytoone;

import io.github.cepr0.putissue.BaseEntity;
import lombok.*;

import javax.persistence.Entity;

/**
 * @author Cepro
 * @since 2017-08-09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class Address extends BaseEntity {
    
    private String street;
}
