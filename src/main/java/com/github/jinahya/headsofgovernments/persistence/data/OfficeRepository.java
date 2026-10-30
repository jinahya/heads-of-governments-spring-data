package com.github.jinahya.headsofgovernments.persistence.data;

import com.github.jinahya.headsofgovernments.persistence.Office;

import java.util.List;

/**
 * A repository of {@link Office}, whose identifier is its {@code country}.
 */
@org.springframework.stereotype.Repository
public interface OfficeRepository
        extends _Repository<Office, String> {

    /**
     * Finds all offices, ordered by their countries.
     *
     * @return a list of all offices.
     */
    List<Office> findAllByOrderByCountryAsc();

    /**
     * Finds all offices, ordered by their countries, each with its heads fetched in the same query.
     *
     * @return a list of all offices, with their heads.
     */
    @org.springframework.data.jpa.repository.EntityGraph(attributePaths = "heads")
    List<Office> findAllFetchHeadsByOrderByCountryAsc();

    /**
     * Finds a page of the offices of the specified head kind, ordered by their countries.
     *
     * @param headKind the head kind.
     * @param pageable the page to find; the countries, which are unique, already make the order
     *                 total, so its sort, if any, sorts only after them.
     * @return the page of the offices of {@code headKind}.
     */
    org.springframework.data.domain.Page<Office> findAllByHeadKindOrderByCountryAsc(
            Office.HeadKind headKind,
            org.springframework.data.domain.Pageable pageable);
}
