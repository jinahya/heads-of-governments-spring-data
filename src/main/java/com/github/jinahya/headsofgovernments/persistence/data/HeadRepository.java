package com.github.jinahya.headsofgovernments.persistence.data;

import com.github.jinahya.headsofgovernments.persistence.Head;
import com.github.jinahya.headsofgovernments.persistence.Office;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * A repository of {@link Head}, whose identifier is a surrogate {@code id}.
 */
public interface HeadRepository
        extends JpaRepository<Head, Long>,
                JpaSpecificationExecutor<Head> {

    /**
     * Finds all heads, in the order the specified sort sorts by.
     *
     * @param sort the sort of the heads.
     * @return a list of all heads.
     */
    @Override
    List<Head> findAll(Sort sort);

    /**
     * Finds a page of all heads, in the order the specified pageable sorts by.
     *
     * @param pageable the page to find, and its sort; make the sort total, e.g., ending with
     *                 {@code id}, for the pages to be.
     * @return the page of all heads.
     */
    @Override
    Page<Head> findAll(Pageable pageable);

    /**
     * Finds all heads of the specified country, ordered by the starts of their terms, then by their
     * identifiers.
     *
     * @param country the country.
     * @return a list of the heads of {@code country}.
     */
    List<Head> findByCountryOrderByTermStartAscIdAsc(String country);

    /**
     * Finds a page of the heads of the specified office, in the order the specified pageable sorts
     * by.
     *
     * @param office   the office.
     * @param pageable the page to find, and its sort; make the sort total, e.g., ending with
     *                 {@code id}, for the pages to be.
     * @return the page of the heads of {@code office}.
     */
    Page<Head> findAllByOffice(Office office, Pageable pageable);
}
