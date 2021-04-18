package com.buildit.validate;

import com.buildit.exception.BadRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InputValidatorTest {

    private final InputValidator underTest = new InputValidator();

    @Test
    public void shouldPassValidationForValidInputUrl() throws BadRequestException {
        // given
        final var url = "http://wiprodigital.com";

        // when
        underTest.validate(url);

        // then
        // no exception should be thrown
    }

    @Test
    public void shouldFailValidationForInvalidInputUrl() {
        // given
        final var url = "invalid-url";

        // when & then
        Assertions.assertThrows(BadRequestException.class, () -> underTest.validate(url));
    }
}
