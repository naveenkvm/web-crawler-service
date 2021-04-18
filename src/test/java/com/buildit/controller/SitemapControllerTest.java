package com.buildit.controller;

import com.buildit.domain.SitemapRequest;
import com.buildit.service.SitemapServiceImpl;
import com.buildit.validate.InputValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {SitemapController.class, SitemapServiceImpl.class, InputValidator.class})
@WebMvcTest
public class SitemapControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnResponseWithLinks() throws Exception {
        // given
        final var baseUrl = "http://wiprodigital.com";
        final var endpoint = "/web/crawl";
        final var request = new SitemapRequest(baseUrl);
        final var requestJson = new ObjectMapper().writeValueAsString(request);
        final var expected = Files.readString(Paths.get("src/test/resources/expectation.json"));

        // when & then
        final var response = mockMvc.perform(post(endpoint)
        .contentType("application/json")
        .content(requestJson))
        .andReturn()
        .getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(201);
        assertThat(response.getContentAsString()).isEqualTo(expected);
    }

    @Test
    public void shouldFailForInvalidInputUrl() throws Exception {
        // given
        final var baseUrl = "an-invalid-url";
        final var endpoint = "/web/crawl";
        final var request = new SitemapRequest(baseUrl);
        final var requestJson = new ObjectMapper().writeValueAsString(request);

        // when
        final var response = mockMvc.perform(post(endpoint)
                .contentType("application/json")
                .content(requestJson))
                .andExpect(status().isBadRequest());
    }
}
