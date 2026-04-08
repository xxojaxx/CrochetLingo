package com.example.crochetLingo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.crochetLingo.antlr.DslSyntaxException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TranslatorServiceTest {

    private TranslatorService translatorService;

    @BeforeEach
    void setUp() {
        translatorService = new TranslatorService();
    }

    @Test
    void translatesBasicFixture() throws IOException {
        String input = readFixture("fixtures/basic.en.dsl");
        String expected = readFixture("fixtures/basic.pl.dsl");

        String actual = translatorService.translate(input, false);

        assertEquals(normalize(expected), normalize(actual));
    }

    @Test
    void translatesContextFixture() throws IOException {
        String input = readFixture("fixtures/context.en.dsl");
        String expected = readFixture("fixtures/context.pl.dsl");

        String actual = translatorService.translate(input, false);

        assertEquals(normalize(expected), normalize(actual));
    }

    @Test
    void throwsForInvalidDsl() {
        assertThrows(DslSyntaxException.class, () -> translatorService.translate("rnd 1: mr 8 sc;", false));
    }

    private String readFixture(String path) throws IOException {
        try (InputStream inputStream = Objects.requireNonNull(
                getClass().getClassLoader().getResourceAsStream(path),
                "Missing fixture: " + path
        )) {
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    private String normalize(String text) {
        return text.replace("\r\n", "\n").trim();
    }
}
