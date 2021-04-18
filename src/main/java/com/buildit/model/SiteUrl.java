package com.buildit.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.io.Serializable;
import java.util.Set;

@Getter
@Builder
public class SiteUrl implements Comparable<SiteUrl>, Serializable {
    private @NonNull String link;
    private Set<String> linksInSameDomain;
    private Set<String> externalLinks;
    private Set<String> images;
    private Set<String> scripts;
    private Set<String> imports;

    public SiteUrl(@NonNull final String link,
                   final Set<String> linksInSameDomain,
                   final Set<String> externalLinks,
                   final Set<String> images,
                   final Set<String> scripts,
                   final Set<String> imports) {
        this.link = link;
        this.linksInSameDomain = linksInSameDomain;
        this.externalLinks = externalLinks;
        this.images = images;
        this.scripts = scripts;
        this.imports = imports;
    }

    @Override
    public int compareTo(SiteUrl o) {
        return this.link.compareTo(o.getLink());
    }
}
