package io.github.cepr0.putissue.onetomany;

import io.github.cepr0.putissue.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

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
public class Parent extends BaseEntity {
    
    private String name;
    
    @OneToMany
    private List<Child> children;
}
