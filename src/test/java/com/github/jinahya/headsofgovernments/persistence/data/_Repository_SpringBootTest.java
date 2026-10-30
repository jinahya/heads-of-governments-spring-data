package com.github.jinahya.headsofgovernments.persistence.data;

import com.github.jinahya.headsofgovernments.persistence.Office;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

/**
 * An abstract {@link SpringBootTest} of a repository, against {@code heads_of_governments.sqlite3},
 * a test resource, as {@code application.yaml} configures it; no {@code persistence.xml}.
 *
 * @param <T> the type of the repository.
 * @param <U> the type of the entities of the repository.
 * @param <V> the type of the identifiers of the entities.
 */
@SpringBootTest
abstract class _Repository_SpringBootTest<T extends _Repository<U, V>, U, V> {

    /**
     * The configuration {@link SpringBootTest} finds, by searching up from this package. The
     * entities are one package up, in the upstream jar, hence the {@link EntityScan}; the
     * repositories are in this package.
     */
    @SpringBootConfiguration
    @EnableAutoConfiguration
    @EntityScan(basePackageClasses = Office.class)
    static class Configuration {

    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Creates a new instance with the specified classes.
     *
     * @param repositoryClass the class of the repository.
     * @param entityClass     the class of the entities of the repository.
     * @param idClass         the class of the identifiers of the entities.
     */
    _Repository_SpringBootTest(final Class<T> repositoryClass, final Class<U> entityClass,
                               final Class<V> idClass) {
        super();
        this.repositoryClass = Objects.requireNonNull(repositoryClass, "repositoryClass is null");
        this.entityClass = Objects.requireNonNull(entityClass, "entityClass is null");
        this.idClass = Objects.requireNonNull(idClass, "idClass is null");
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * The class of the repository.
     */
    final Class<T> repositoryClass;

    /**
     * The class of the entities of the repository.
     */
    final Class<U> entityClass;

    /**
     * The class of the identifiers of the entities.
     */
    final Class<V> idClass;

    // ---------------------------------------------------------------------------------------------

    /**
     * The instance of the repository, which Spring injects.
     */
    @Autowired
    @Accessors(fluent = true)
    @Getter(AccessLevel.PACKAGE)
    private T repositoryInstance;
}
