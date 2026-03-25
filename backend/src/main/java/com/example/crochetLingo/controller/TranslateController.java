package com.example.crochetLingo.controller;

import com.example.crochetLingo.model.PatternRequest;
import com.example.crochetLingo.model.PatternResponse;
import com.example.crochetLingo.model.PatternText;
import org.springframework.web.bind.annotation.*;
import com.example.crochetLingo.service.TranslatorService;

@RestController
@RequestMapping("/api/translate")
@CrossOrigin(origins = "*")
public class TranslateController {

    @PostMapping
    public PatternResponse translate(@RequestBody PatternRequest request) {
        String translated = TranslatorService.translate(request.pattern_en.text);

        PatternResponse response = new PatternResponse();
        response.pattern_en = request.pattern_en;
        response.pattern_pl = new PatternText();
        response.pattern_pl.text = translated;
        return response;
    }
}