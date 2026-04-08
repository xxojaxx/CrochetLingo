package com.example.crochetLingo.service;

import com.example.crochetLingo.antlr.CrochetLexer;
import com.example.crochetLingo.antlr.CrochetParser;
import com.example.crochetLingo.antlr.CrochetToPolishVisitor;
import com.example.crochetLingo.antlr.ThrowingErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.springframework.stereotype.Service;

@Service
public class TranslatorService {

    public String translate(String input, boolean mirror) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("pattern_en.text must not be blank");
        }

        CrochetLexer lexer = new CrochetLexer(CharStreams.fromString(input));
        lexer.removeErrorListeners();
        lexer.addErrorListener(ThrowingErrorListener.INSTANCE);

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CrochetParser parser = new CrochetParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(ThrowingErrorListener.INSTANCE);

        CrochetToPolishVisitor visitor = new CrochetToPolishVisitor(mirror);
        return visitor.visit(parser.pattern());
    }
}