package com.github.jinahya.headsofgovernments.persistence.data;

import com.github.jinahya.headsofgovernments.persistence.Head;
import com.github.jinahya.headsofgovernments.persistence.Office;

import java.util.List;

/**
 * A repository of {@link Head}, whose identifier is a surrogate {@code id}.
 */
@org.springframework.stereotype.Repository
public interface HeadRepository
        extends _Repository<Head, Long> {

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
    org.springframework.data.domain.Page<Head> findAllByOffice(
            Office office,
            org.springframework.data.domain.Pageable pageable);
}
