package com.example.crochetLingo.antlr;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CrochetToPolishVisitor extends CrochetBaseVisitor<String> {


    private static final Map<String, String> STITCH_TRANSLATIONS = Map.of(
            "sc", "ps",
            "hdc", "psn",
            "dc", "s",
            "ch", "ł",
            "slst", "oś",
            "inc", "zw",
            "dec", "zm",
            "flo", "po",
            "blo", "to"
    );

    @Override
    public String visitPattern(CrochetParser.PatternContext ctx) {
        return ctx.round().stream()
                .map(this::visit)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String visitRound(CrochetParser.RoundContext ctx) {
        return "okr " + ctx.NUMBER().getText() + ": " + visit(ctx.elementList()) + ";";
    }

    @Override
    public String visitElementList(CrochetParser.ElementListContext ctx) {
        return ctx.element().stream()
                .map(this::visit)
                .collect(Collectors.joining(", "));
    }

    @Override
    public String visitElement(CrochetParser.ElementContext ctx) {
        return visit(ctx.getChild(0));
    }

    @Override
    public String visitStitch(CrochetParser.StitchContext ctx) {
        String translated = STITCH_TRANSLATIONS.get(ctx.STITCH().getText());
        if (translated == null) {
            throw new DslSyntaxException("Unsupported stitch token: " + ctx.STITCH().getText());
        }

        StringBuilder result = new StringBuilder(translated);

        if (ctx.NUMBER() != null) {
            result.append(" ").append(ctx.NUMBER().getText());
        }

        if (ctx.contextOperation() != null) {
            result.append(" ").append(visit(ctx.contextOperation()));
        }

        return result.toString();
    }

    @Override
    public String visitAction(CrochetParser.ActionContext ctx) {
        return switch (ctx.getText()) {
            case "mr" -> "mk";
            case "fo" -> "zr";
            default -> throw new DslSyntaxException("Unsupported action token: " + ctx.getText());
        };
    }

    @Override
    public String visitRepeat(CrochetParser.RepeatContext ctx) {
        String inner = visit(ctx.elementList());
        return "(" + inner + ") x " + ctx.NUMBER().getText();
    }

    @Override
    public String visitContextOperation(CrochetParser.ContextOperationContext ctx) {
        String text = ctx.getText();

        if ("in_next_st".equals(text)) {
            return "w nast. ocz";
        }

        if ("in_same_st".equals(text)) {
            return "w to samo ocz";
        }

        if ("turn".equals(text)) {
            return "obróć";
        }

        if (text.startsWith("skip")) {
            if (ctx.NUMBER() == null) {
                return "omiń oczko";
            }
            return "omiń " + ctx.NUMBER().getText() + " oczek";
        }

        throw new DslSyntaxException("Unsupported context operation: " + text);
    }
}
