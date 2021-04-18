package com.buildit.controller;

import com.buildit.domain.SitemapRequest;
import com.buildit.exception.BadRequestException;
import com.buildit.model.SiteUrl;
import com.buildit.service.SitemapService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/web")
public class SitemapController {

    private final SitemapService sitemapService;

    @Autowired
    public SitemapController(final SitemapService sitemapService) {
        this.sitemapService = sitemapService;
    }

    @ApiOperation(value = "Creates web crawler and returns site map for a url", response = SiteUrl.class, responseContainer = "Set")
    @RequestMapping(method = POST, path = "/crawl")
    @ResponseStatus(CREATED)
    public @ResponseBody
    Set<SiteUrl> createWebCrawler(@Valid @RequestBody final SitemapRequest request) throws BadRequestException {
        return sitemapService.crawlUrl(request.getBaseUrl());
    }

}
