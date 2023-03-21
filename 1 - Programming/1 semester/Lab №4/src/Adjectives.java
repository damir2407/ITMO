public enum Adjectives {
    LOW("низкое"),
    LONG("длинное"),
    HANGING("висячие"),
    CLOSED("закрытое"),
    OPEN("открытое"),
    CALM("спокойное"),
    ACTIVE("активное"),
    GLASS("стеклянный"),
    TINY("крохотный"),
    SMALL("маленький"),
    BIG("большой"),
    EXTRA_LONG("очень длинная"),
    NARROW("узенький"),
    BENT("согнутый"),
    WONDERFUL("чудесный"),
    COOL("прохладные"),
    FOLDING("складной");

    private final String TRANSLATE;

    Adjectives(String translate) {
        this.TRANSLATE = translate;
    }

    public String getTRANSLATE() {
        return TRANSLATE;
    }
}
