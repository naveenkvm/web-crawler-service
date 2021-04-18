package com.buildit.validate;

import com.buildit.exception.BadRequestException;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

@Component
public class InputValidator {

    public void validate(final String url) throws BadRequestException {
        try {
            new URL(url).toURI();
        } catch (final IllegalArgumentException | MalformedURLException | URISyntaxException e) {
            throw new BadRequestException(String.format("Invalid input url %s", url));
        }
    }
}
