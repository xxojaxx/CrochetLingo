package com.example.crochetLingo.antlr;

import java.util.List;

public class DslSyntaxException extends RuntimeException {

    private final int line;
    private final int column;
    private final String offendingToken;
    private final List<String> expected;

    public DslSyntaxException(String message) {
        super(message);
        this.line = -1;
        this.column = -1;
        this.offendingToken = null;
        this.expected = List.of();
    }

    public DslSyntaxException(
            String message,
            int line,
            int column,
            String offendingToken,
            List<String> expected,
            Throwable cause
    ) {
        super(message, cause);
        this.line = line;
        this.column = column;
        this.offendingToken = offendingToken;
        this.expected = expected;
    }

    public int getLine() { return line; }
    public int getColumn() { return column; }
    public String getOffendingToken() { return offendingToken; }
    public List<String> getExpected() { return expected; }
}