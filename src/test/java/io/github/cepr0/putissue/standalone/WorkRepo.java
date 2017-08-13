package io.github.cepr0.putissue.standalone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Cepro
 * @since 2017-08-13
 */
@RepositoryRestResource
public interface WorkRepo extends JpaRepository<Work, Integer> {
}
