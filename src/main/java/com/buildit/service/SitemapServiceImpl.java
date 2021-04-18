package com.buildit.service;

import com.buildit.exception.BadRequestException;
import com.buildit.model.SiteUrl;
import com.buildit.validate.InputValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Set;

@Service
@Slf4j
public class SitemapServiceImpl implements SitemapService {

    private final InputValidator inputValidator;

    @Autowired
    public SitemapServiceImpl(final InputValidator inputValidator) {
        this.inputValidator = inputValidator;
    }

    public Set<SiteUrl> crawlUrl(final String baseUrl) throws BadRequestException {
        inputValidator.validate(baseUrl);
        final var domain = URI.create(baseUrl).getHost();
        final var webCrawler = new WebCrawler(domain);
        webCrawler.visitIfNotAlreadyVisited(baseUrl);
        return webCrawler.getLinks();
    }


}
