package com.github.jinahya.headsofgovernments.persistence.data;

import com.github.jinahya.headsofgovernments.persistence.Head;
import com.github.jinahya.headsofgovernments.persistence.HeadId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A {@link _Repository_SpringBootTest} of {@link HeadRepository}.
 */
class HeadRepository_SpringBootTest
        extends _Repository_SpringBootTest<HeadRepository, Head, HeadId> {

    /**
     * Creates a new instance.
     */
    HeadRepository_SpringBootTest() {
        super(HeadRepository.class, Head.class, HeadId.class);
    }

    // ---------------------------------------------------------------------------------------------

    @DisplayName("findAll(Sort)")
    @Nested
    class FindAll_Sort {

        @Test
        void countryAscTermStartAsc() {
            final var heads = repositoryInstance().findAll(
                    org.springframework.data.domain.Sort.by("country", "termStart")
            );
            assertThat(heads).hasSize(285);
            assertThat(heads).isSortedAccordingTo(
                    java.util.Comparator.comparing((Head h) -> h.getOffice().getCountry())
                                            .thenComparing(Head::getTermStart));
        }
    }

    @DisplayName("findAll(Pageable)")
    @Nested
    class FindAll_Pageable {

        @Test
        void countryAscTermStartAsc_firstPage() {
            final var page = repositoryInstance().findAll(
                    org.springframework.data.domain.PageRequest.of(
                            0, 10, org.springframework.data.domain.Sort.by("country", "termStart")
                    )
            );
            assertThat(page.getContent()).hasSize(10);
            assertThat(page.getContent()).isSortedAccordingTo(
                    java.util.Comparator.comparing((Head h) -> h.getOffice().getCountry())
                                            .thenComparing(Head::getTermStart));
            assertThat(page.getTotalElements()).isEqualTo(285L);
        }
    }

    @DisplayName("findByCountryOrderByTermStartAsc(String)")
    @Nested
    class FindByCountryOrderByTermStartAsc {

        @Test
        void KR() {
            final var heads = repositoryInstance().findByCountryOrderByTermStartAsc("KR");
            assertThat(heads).hasSize(21);
            assertThat(heads).extracting(Head::getTermStart).isSorted();
            assertThat(heads.getFirst().getName()).isEqualTo("이승만");
        }
    }

    @DisplayName("findAllByOffice(Office, Pageable)")
    @Nested
    class FindAllByOffice {

        @Test
        void KR_termStartDesc_firstPage() {
            final var office = officeRepository.findById("KR").orElseThrow();
            final var page = repositoryInstance().findAllByOffice(
                    office,
                    org.springframework.data.domain.PageRequest.of(
                            0, 10,
                            org.springframework.data.domain.Sort.by("termStart").descending()
                    )
            );
            assertThat(page.getContent()).hasSize(10);
            assertThat(page.getContent()).extracting(Head::getName).startsWith("이재명", "윤석열");
            assertThat(page.getTotalElements()).isEqualTo(21L);
        }
    }

    // ---------------------------------------------------------------------------------------------
    @Autowired
    private OfficeRepository officeRepository;
}
