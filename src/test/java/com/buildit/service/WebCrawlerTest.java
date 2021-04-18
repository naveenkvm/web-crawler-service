package com.buildit.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class WebCrawlerTest {

    private final WebCrawler underTest = new WebCrawler("wiprodigital.com");

    @Test
    public void shouldCrawlAndPopulateLinks() {
        // given
        final var url = "http://wiprodigital.com";

        // when
        underTest.visitIfNotAlreadyVisited(url);

        // then
        final var siteUrls = underTest.getLinks();
        Assertions.assertThat(siteUrls.size()).isEqualTo(1);
        final var siteUrl = siteUrls.iterator().next();
        Assertions.assertThat(siteUrl.getExternalLinks()).isNotEmpty();
        Assertions.assertThat(siteUrl.getImages()).isNotEmpty();
        Assertions.assertThat(siteUrl.getImports()).isNotEmpty();
        Assertions.assertThat(siteUrl.getScripts()).isNotEmpty();
        Assertions.assertThat(siteUrl.getLinksInSameDomain()).isEmpty();
    }
}
