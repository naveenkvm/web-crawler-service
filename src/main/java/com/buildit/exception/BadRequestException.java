package com.buildit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Actor Not Found")
public class BadRequestException extends Exception {

    public BadRequestException(final String message) {
        super(message);
    }
}
