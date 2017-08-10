package io.github.cepr0.putissue.bi_onetomany;

import io.github.cepr0.putissue.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import static lombok.AccessLevel.PROTECTED;

/**
 * @author Cepro
 *         2017-08-10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class BiChild extends BaseEntity {

    @ManyToOne
    private BiParent parent;

    private String name;
}
