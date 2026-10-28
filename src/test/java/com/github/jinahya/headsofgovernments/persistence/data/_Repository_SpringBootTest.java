package com.github.jinahya.headsofgovernments.persistence.data;

import com.github.jinahya.headsofgovernments.persistence.Office;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * An abstract {@link SpringBootTest} of a repository, against {@code heads_of_governments.sqlite3},
 * a test resource, as {@code application.yaml} configures it; no {@code persistence.xml}.
 */
@SpringBootTest
abstract class _Repository_SpringBootTest {

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
}
