package com.github.jinahya.headsofgovernments.persistence.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * The repository the repositories of this package extend, with what they have in common.
 *
 * @param <T> the type of the entities.
 * @param <U> the type of the identifiers of the entities.
 */
@NoRepositoryBean
interface _Repository<T, U>
        extends JpaRepository<T, U>,
                JpaSpecificationExecutor<T> {

}
