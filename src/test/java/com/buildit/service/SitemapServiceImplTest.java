package com.buildit.service;

import com.buildit.exception.BadRequestException;
import com.buildit.validate.InputValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SitemapServiceImplTest {
    @Mock
    private InputValidator inputValidator;

    private SitemapService underTest;

    @BeforeEach
    public void setUp() {
        underTest = new SitemapServiceImpl(inputValidator);
    }

    @Test
    public void shouldGetSiteLinks() throws BadRequestException {
        // given
        final var url = "http://wiprodigital.com";

        // when
        final var siteUrls = underTest.crawlUrl(url);

        // then
        verify(inputValidator).validate(url);
        Assertions.assertThat(siteUrls.size()).isEqualTo(1);
        final var siteUrl = siteUrls.iterator().next();
        Assertions.assertThat(siteUrl.getExternalLinks()).isNotEmpty();
        Assertions.assertThat(siteUrl.getImages()).isNotEmpty();
        Assertions.assertThat(siteUrl.getImports()).isNotEmpty();
        Assertions.assertThat(siteUrl.getScripts()).isNotEmpty();
        Assertions.assertThat(siteUrl.getLinksInSameDomain()).isEmpty();
    }

}
