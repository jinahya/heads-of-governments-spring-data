package com.github.jinahya.headsofgovernments.persistence.data;

import com.github.jinahya.headsofgovernments.persistence.Office;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.assertThat;

class OfficeRepository_SpringBootTest extends _Repository_SpringBootTest {

    @DisplayName("findAll(Sort)")
    @Nested
    class FindAll_Sort {

        @Test
        void countryDesc() {
            assertThat(repository.findAll(Sort.by("country").descending()))
                    .extracting(Office::getCountry)
                    .containsExactly("US", "KR", "JP", "GB", "FR");
        }
    }

    @DisplayName("findAll(Pageable)")
    @Nested
    class FindAll_Pageable {

        @Test
        void countryAsc_firstPage() {
            final var page = repository.findAll(
                    PageRequest.of(0, 2, Sort.by("country"))
            );
            assertThat(page.getContent()).extracting(Office::getCountry).containsExactly("FR", "GB");
            assertThat(page.getTotalElements()).isEqualTo(5L);
        }
    }

    @DisplayName("findAllByOrderByCountryAsc()")
    @Nested
    class FindAllByOrderByCountryAsc {

        @Test
        void five() {
            assertThat(repository.findAllByOrderByCountryAsc())
                    .extracting(Office::getCountry)
                    .containsExactly("FR", "GB", "JP", "KR", "US");
        }
    }

    @DisplayName("findByCountry(String)")
    @Nested
    class FindByCountry {

        @Test
        void KR() {
            assertThat(repository.findByCountry("KR"))
                    .hasValueSatisfying(o -> assertThat(o.getHeadKind()).isEqualTo("PRESIDENT"));
        }
    }

    @Autowired
    private OfficeRepository repository;
}
