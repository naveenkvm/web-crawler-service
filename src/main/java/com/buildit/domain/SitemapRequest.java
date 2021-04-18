package com.buildit.domain;


import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
@Getter
public class SitemapRequest implements Serializable {
    private @NonNull String baseUrl;

    public SitemapRequest(){}
}
