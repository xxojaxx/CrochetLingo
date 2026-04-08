package com.example.crochetLingo.antlr;

import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collections;

public class CrochetToPolishVisitor extends CrochetBaseVisitor<String> {

    private final boolean mirror;

    public CrochetToPolishVisitor(boolean mirror) {
        this.mirror = mirror;
    }

    // konstruktor domyślny dla wstecznej kompatybilności
    public CrochetToPolishVisitor() {
        this.mirror = false;
    }

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
        List<TerminalNode> numbers = ctx.NUMBER();

        String range;
        if (numbers.size() == 2) {
            range = numbers.get(0).getText() + "-" + numbers.get(1).getText();
        } else {
            range = numbers.get(0).getText();
        }

        return "okr " + range + ": " + visit(ctx.elementList()) + ";";
    }

    @Override
    public String visitElementList(CrochetParser.ElementListContext ctx) {
        List<CrochetParser.ElementContext> elements = new ArrayList<>(ctx.element());

        if (mirror) {
            // wyodrębnij mr z początku listy jeśli istnieje
            int startIndex = 0;
            if (!elements.isEmpty()) {
                String firstText = elements.get(0).getText();
                if ("mr".equals(firstText)) {
                    startIndex = 1;
                }
            }

            List<CrochetParser.ElementContext> prefix = elements.subList(0, startIndex);
            List<CrochetParser.ElementContext> rest = new ArrayList<>(elements.subList(startIndex, elements.size()));
            Collections.reverse(rest);

            elements = new ArrayList<>(prefix);
            elements.addAll(rest);
        }

        return elements.stream()
                .map(this::visit)
                .collect(Collectors.joining(", "));
    }

    @Override
    public String visitElement(CrochetParser.ElementContext ctx) {
        return visit(ctx.getChild(0));
    }

    @Override
    public String visitStitch(CrochetParser.StitchContext ctx) {
        String stitchToken = ctx.STITCH().getText();
        String translated = STITCH_TRANSLATIONS.get(stitchToken);

        if (translated == null) {
            throw new DslSyntaxException("Unsupported stitch token: " + stitchToken);
        }

        StringBuilder result = new StringBuilder();

        TerminalNode numberNode = ctx.NUMBER();

        if (numberNode != null) {
            String number = numberNode.getText();

            if (ctx.getChild(0).getText().equals(number)) {
                result.append(number).append(" ").append(translated);
            } else {
                result.append(translated).append(" ").append(number);
            }
        } else {
            result.append(translated);
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