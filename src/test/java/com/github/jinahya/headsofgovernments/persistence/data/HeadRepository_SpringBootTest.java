package com.github.jinahya.headsofgovernments.persistence.data;

import com.github.jinahya.headsofgovernments.persistence.Head;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.assertThat;

class HeadRepository_SpringBootTest extends _Repository_SpringBootTest {

    @DisplayName("findAll(Sort)")
    @Nested
    class FindAll_Sort {

        @Test
        void idAsc() {
            final var heads = repository.findAll(Sort.by("id"));
            assertThat(heads).hasSize(285);
            assertThat(heads).extracting(Head::getId).isSorted();
        }
    }

    @DisplayName("findAll(Pageable)")
    @Nested
    class FindAll_Pageable {

        @Test
        void idAsc_firstPage() {
            final var page = repository.findAll(
                    PageRequest.of(0, 10, Sort.by("id"))
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
            final var heads = repository.findByCountryOrderByTermStartAscIdAsc("KR");
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
            final var office = officeRepository.findByCountry("KR").orElseThrow();
            final var page = repository.findAllByOffice(
                    office,
                    PageRequest.of(0, 10, Sort.by("termStart", "id").descending())
            );
            assertThat(page.getContent()).hasSize(10);
            assertThat(page.getContent()).extracting(Head::getName).startsWith("이재명", "윤석열");
            assertThat(page.getTotalElements()).isEqualTo(21L);
        }
    }

    // ---------------------------------------------------------------------------------------------
    @Autowired
    private HeadRepository repository;

    @Autowired
    private OfficeRepository officeRepository;
}
