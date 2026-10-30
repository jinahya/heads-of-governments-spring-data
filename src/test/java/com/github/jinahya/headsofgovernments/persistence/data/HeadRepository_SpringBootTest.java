package com.github.jinahya.headsofgovernments.persistence.data;

import com.github.jinahya.headsofgovernments.persistence.Head;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A {@link _Repository_SpringBootTest} of {@link HeadRepository}.
 */
class HeadRepository_SpringBootTest
        extends _Repository_SpringBootTest<HeadRepository, Head, Long> {

    /**
     * Creates a new instance.
     */
    HeadRepository_SpringBootTest() {
        super(HeadRepository.class, Head.class, Long.class);
    }

    // ---------------------------------------------------------------------------------------------

    @DisplayName("findAll(Sort)")
    @Nested
    class FindAll_Sort {

        @Test
        void idAsc() {
            final var heads = repositoryInstance().findAll(
                    org.springframework.data.domain.Sort.by("id")
            );
            assertThat(heads).hasSize(285);
            assertThat(heads).extracting(Head::getId).isSorted();
        }
    }

    @DisplayName("findAll(Pageable)")
    @Nested
    class FindAll_Pageable {

        @Test
        void idAsc_firstPage() {
            final var page = repositoryInstance().findAll(
                    org.springframework.data.domain.PageRequest.of(
                            0, 10, org.springframework.data.domain.Sort.by("id")
                    )
            );
            assertThat(page.getContent()).hasSize(10);
            assertThat(page.getContent()).extracting(Head::getId).isSorted();
            assertThat(page.getTotalElements()).isEqualTo(285L);
        }
    }

    @DisplayName("findByCountryOrderByTermStartAscIdAsc(String)")
    @Nested
    class FindByCountryOrderByTermStartAscIdAsc {

        @Test
        void KR() {
            final var heads = repositoryInstance().findByCountryOrderByTermStartAscIdAsc("KR");
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
                            org.springframework.data.domain.Sort.by("termStart", "id").descending()
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
