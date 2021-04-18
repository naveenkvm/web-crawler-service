package com.buildit.service;

import com.buildit.exception.BadRequestException;
import com.buildit.model.SiteUrl;

import java.util.Set;

public interface SitemapService {

    Set<SiteUrl> crawlUrl(final String baseUrl) throws BadRequestException;
}
