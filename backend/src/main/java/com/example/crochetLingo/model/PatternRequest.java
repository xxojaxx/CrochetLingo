package com.example.crochetLingo.model;

public class PatternRequest {
    public PatternText pattern_en;
    private boolean mirror;

    public PatternText getPattern_en() { return pattern_en; }
    public void setPattern_en(PatternText pattern_en) { this.pattern_en = pattern_en; }

    public boolean isMirror() { return mirror; }
    public void setMirror(boolean mirror) { this.mirror = mirror; }
}