package io.github.cepr0.putissue.onetomany;

import io.github.cepr0.putissue.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

/**
 * @author Cepro
 * @since 2017-08-09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@ToString(callSuper = true, exclude = "children")
@EqualsAndHashCode(callSuper = true, exclude = "children")
@Entity
public class Parent extends BaseEntity {
    
    private String name;
    
    @OneToMany
    private List<Child> children;
}
