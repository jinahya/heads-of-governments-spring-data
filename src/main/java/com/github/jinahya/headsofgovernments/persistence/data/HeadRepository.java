package com.github.jinahya.headsofgovernments.persistence.data;

import com.github.jinahya.headsofgovernments.persistence.Head;
import com.github.jinahya.headsofgovernments.persistence.HeadId;
import com.github.jinahya.headsofgovernments.persistence.Office;

import java.util.List;

/**
 * A repository of {@link Head}, whose identifier is a {@link HeadId}, of the key
 * {@code (country, term_start)}.
 */
@org.springframework.stereotype.Repository
public interface HeadRepository
        extends _Repository<Head, HeadId> {

    /**
     * Finds all heads of the specified country, ordered by the starts of their terms, which are
     * unique within a country.
     *
     * @param country the country.
     * @return a list of the heads of {@code country}.
     */
    List<Head> findByCountryOrderByTermStartAsc(String country);

    /**
     * Finds a page of the heads of the specified office, in the order the specified pageable sorts
     * by.
     *
     * @param office   the office.
     * @param pageable the page to find, and its sort; make the sort total, e.g., with
     *                 {@code termStart}, for the pages to be.
     * @return the page of the heads of {@code office}.
     */
    org.springframework.data.domain.Page<Head> findAllByOffice(
            Office office,
            org.springframework.data.domain.Pageable pageable);
}
