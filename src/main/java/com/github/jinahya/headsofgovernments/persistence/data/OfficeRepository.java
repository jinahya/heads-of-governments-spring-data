package com.github.jinahya.headsofgovernments.persistence.data;

import com.github.jinahya.headsofgovernments.persistence.Office;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

/**
 * A repository of {@link Office}, whose identifier is its {@code country}.
 */
public interface OfficeRepository
        extends Repository<Office, String>,
                JpaSpecificationExecutor<Office> {

    /**
     * Finds all offices, in the order the specified sort sorts by.
     *
     * @param sort the sort of the offices.
     * @return a list of all offices.
     */
    List<Office> findAll(Sort sort);

    /**
     * Finds a page of all offices, in the order the specified pageable sorts by.
     *
     * @param pageable the page to find, and its sort; make the sort total, e.g., ending with
     *                 {@code country}, for the pages to be.
     * @return the page of all offices.
     */
    Page<Office> findAll(Pageable pageable);

    /**
     * Finds the office of the specified country.
     *
     * @param country the country.
     * @return an optional of the office of {@code country}; empty if none.
     */
    Optional<Office> findByCountry(String country);

    /**
     * Finds all offices, ordered by their countries.
     *
     * @return a list of all offices.
     */
    List<Office> findAllByOrderByCountryAsc();
}
