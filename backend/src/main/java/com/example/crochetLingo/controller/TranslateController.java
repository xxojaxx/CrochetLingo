package com.example.crochetLingo.controller;

import com.example.crochetLingo.antlr.DslSyntaxException;
import com.example.crochetLingo.model.PatternRequest;
import com.example.crochetLingo.model.PatternResponse;
import com.example.crochetLingo.model.PatternText;
import com.example.crochetLingo.service.TranslatorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/translate")
@CrossOrigin(origins = "*")
public class TranslateController {

    private final TranslatorService translatorService;

    public TranslateController(TranslatorService translatorService) {
        this.translatorService = translatorService;
    }

    @PostMapping
    public PatternResponse translate(@RequestBody PatternRequest request) {
        if (request == null || request.pattern_en == null || request.pattern_en.text == null || request.pattern_en.text.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "pattern_en.text is required");
        }

        String translated = translatorService.translate(request.pattern_en.text);

        PatternResponse response = new PatternResponse();
        response.pattern_en = request.pattern_en;
        response.pattern_pl = new PatternText();
        response.pattern_pl.text = translated;
        return response;
    }
}
