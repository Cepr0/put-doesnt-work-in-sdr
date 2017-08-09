package io.github.cepr0.putissue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Cepro
 * @since 2017-08-09
 */
@RepositoryRestResource(exported = false)
public interface BaseRepo extends JpaRepository<BaseEntity, Integer> {
}
