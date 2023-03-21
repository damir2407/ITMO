public enum Feelings {
    BIG_REGRET("большое сожаление"),
    STANDARD("стандартное"),
    SADLY("уныло"),
    HAPPY("радость");

    private final String TRANSLATE;

    Feelings(String translate) {
        this.TRANSLATE = translate;
    }

    public String getTRANSLATE() {
        return TRANSLATE;
    }
}


