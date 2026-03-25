package com.example.crochetLingo.service;

import org.springframework.stereotype.Service;

@Service
public class TranslatorService {

    public static String translate(String input) {
        // NA RAZIE MOCK
        return "PRZETŁUMACZONE: " + input;

        // później:
        // return antlrParser.parse(input);
    }
}