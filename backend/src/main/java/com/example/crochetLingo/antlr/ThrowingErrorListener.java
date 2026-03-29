package com.example.crochetLingo.antlr;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.IntervalSet;

import java.util.List;
import java.util.stream.Collectors;

public final class ThrowingErrorListener extends BaseErrorListener {

    public static final ThrowingErrorListener INSTANCE = new ThrowingErrorListener();

    private ThrowingErrorListener() {
    }

    @Override
    public void syntaxError(
            Recognizer<?, ?> recognizer,
            Object offendingSymbol,
            int line,
            int charPositionInLine,
            String msg,
            RecognitionException e
    ) {

        String token = offendingSymbol instanceof Token
                ? ((Token) offendingSymbol).getText()
                : null;

        IntervalSet expectedTokens = ((Parser) recognizer).getExpectedTokens();

        Vocabulary vocab = ((Parser) recognizer).getVocabulary();

        List<String> expected = expectedTokens.toList().stream()
                .map(vocab::getDisplayName)
                .collect(Collectors.toList());

        throw new DslSyntaxException(
                "Syntax error",
                line,
                charPositionInLine + 1,
                token,
                expected,
                e
        );
    }
}
