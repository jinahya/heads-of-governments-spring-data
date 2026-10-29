package com.github.jinahya.headsofgovernments.persistence.data;

import com.github.jinahya.headsofgovernments.persistence.Office;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class OfficeRepository_SpringBootTest
        extends _Repository_SpringBootTest {

    private static List<String> countryList() {
        return List.of("FR", "GB", "JP", "KR", "US");
    }

    // ---------------------------------------------------------------------------------------------
    @DisplayName("findAll(Sort)")
    @Nested
    class FindAll_Sort {

        @Test
        void countryAsc() {
            assertThat(repository.findAll(Sort.by("country").ascending()))
                    .extracting(Office::getCountry)
                    .containsExactlyElementsOf(countryList());
        }

        @Test
        void countryDesc() {
            assertThat(repository.findAll(Sort.by("country").descending()))
                    .extracting(Office::getCountry)
                    .containsExactlyElementsOf(countryList().reversed());
        }
    }

    // ---------------------------------------------------------------------------------------------
    @DisplayName("findAll(Pageable)")
    @Nested
    class FindAll_Pageable {

        @Test
        void countryAsc() {
            final var pageSize = ThreadLocalRandom.current().nextInt(1, countryList().size() + 1);
            final var sort = Sort.by("country").ascending();
            for (var p = PageRequest.of(0, pageSize, sort); ; p = p.next()) {
                final var page = repository.findAll(p);
                assertThat(page.getTotalElements()).isEqualTo(countryList().size());
                final var content = page.getContent();
                log.debug("page: {}, content: {}", p, content);
                assertThat(content).hasSizeLessThanOrEqualTo(pageSize);
                if (content.isEmpty()) {
                    break;
                }
                assertThat(content).extracting(Office::getCountry).isSorted();
            }
        }

        @Test
        void countryDesc() {
            final var pageSize = ThreadLocalRandom.current().nextInt(1, countryList().size() + 1);
            final var sort = Sort.by("country").descending();
            for (var p = PageRequest.of(0, pageSize, sort); ; p = p.next()) {
                final var page = repository.findAll(p);
                assertThat(page.getTotalElements()).isEqualTo(countryList().size());
                final var content = page.getContent();
                log.debug("page: {}, content: {}", p, content);
                assertThat(content).hasSizeLessThanOrEqualTo(pageSize);
                if (content.isEmpty()) {
                    break;
                }
                assertThat(content).extracting(Office::getCountry)
                        .isSortedAccordingTo(Comparator.reverseOrder());
            }
        }
    }

    // ---------------------------------------------------------------------------------------------
    @DisplayName("findAllByOrderByCountryAsc()")
    @Nested
    class FindAllByOrderByCountryAsc {

        @Test
        void five() {
            assertThat(repository.findAllByOrderByCountryAsc())
                    .extracting(Office::getCountry)
                    .containsExactlyElementsOf(countryList());
        }
    }

    // ---------------------------------------------------------------------------------------------
    @DisplayName("findByCountry(String)")
    @Nested
    class FindByCountry {

        private static List<String> countryList() {
            return OfficeRepository_SpringBootTest.countryList();
        }

        @Test
        void _Empty_KP() {
            assertThat(repository.findByCountry("KP")).isEmpty();
        }

        @MethodSource("countryList")
        @ParameterizedTest
        void __(final String country) {
            assertThat(repository.findByCountry(country))
                    .hasValueSatisfying(o -> assertThat(o.getCountry()).isEqualTo(country));
        }
    }

    // ---------------------------------------------------------------------------------------------
    @Autowired
    private OfficeRepository repository;
}
