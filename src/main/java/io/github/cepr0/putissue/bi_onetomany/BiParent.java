package io.github.cepr0.putissue.bi_onetomany;

import io.github.cepr0.putissue.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

/**
 * @author Cepro
 *         2017-08-10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@ToString(callSuper = true, exclude = "children")
@EqualsAndHashCode(callSuper = true, exclude = "children")
@Entity
public class BiParent extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "parent")
    private List<BiChild> children;

    public BiParent setChildren(List<BiChild> children) {

        // release previous children
        if (this.children != null) {
            for (BiChild child : this.children) {
                child.setParent(null);
            }
        }

        // link new children
        for (BiChild child : children) {
            child.setParent(this);
        }

        this.children = children;
        return this;
    }
}
