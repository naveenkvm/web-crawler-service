package com.buildit.service;

import com.buildit.model.SiteUrl;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Node;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Slf4j
@Getter
public class WebCrawler {

    private final Set<String> visited = new TreeSet<>();
    private final Set<SiteUrl> links = new TreeSet<>();
    private final String domain;

    public WebCrawler(final String domain) {
        this.domain = domain;
    }

    public void visitIfNotAlreadyVisited(final String visitUrl) {
        if (!visited.contains(visitUrl)) {
            try {
                links.add(visitLink(visitUrl));
                visited.add(visitUrl);
            } catch (final IOException e) {
                log.error("Error when visiting link {} ", visitUrl, e);
            }
        }
    }

    private SiteUrl visitLink(final String link) throws IOException {
        final var doc = Jsoup.connect(link).get();
        final var images = doc.getElementsByTag("img").stream().map(Node::outerHtml).collect(Collectors.toSet());
        final var scripts = doc.getElementsByTag("script").stream().map(Node::outerHtml).collect(Collectors.toSet());
        final var imports = doc.select("link[href]").stream().map(Node::outerHtml).collect(Collectors.toSet());
        final var linksOnSameDomainElements = doc.select("a[href^=" + domain + "]");
        final var linksOnSameDomain = linksOnSameDomainElements.stream().map(Node::outerHtml).collect(Collectors.toSet());
        final var externalLinks = doc.select("a[href~=^((?!" + domain + ").)*$]").stream().map(Node::outerHtml).collect(Collectors.toSet());
        final var page = new SiteUrl(link, linksOnSameDomain, externalLinks, images, scripts, imports);
        linksOnSameDomainElements.forEach(element -> visitIfNotAlreadyVisited(element.attr("abs:href")));
        return page;
    }


}
