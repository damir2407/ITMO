public enum Feelings {
    INDELIBLE_IMPRESSION("неизгладимое впечатление"),
    FIRST("впервые"),
    IMPUNITY("безнаказанно");
    private final String TRANSLATE;

    Feelings(String translate) {
        this.TRANSLATE = translate;
    }

    public String getTRANSLATE() {
        return TRANSLATE;
    }
}
