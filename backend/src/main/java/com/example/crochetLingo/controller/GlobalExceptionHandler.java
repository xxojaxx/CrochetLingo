package com.example.crochetLingo.controller;

import com.example.crochetLingo.antlr.DslSyntaxException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DslSyntaxException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleDslError(DslSyntaxException ex) {
        return Map.of(
                "message", ex.getMessage(),
                "line", ex.getLine(),
                "column", ex.getColumn(),
                "offendingToken", ex.getOffendingToken(),
                "expected", ex.getExpected()
        );
    }
}