public enum Things {

    RIFLE("винтовка"),
    CHAIRS("стулья"),
    WEAPON("оружие"),
    BROADCAST("передачу");
    private final String TRANSLATE;

    Things(String translate) {
        this.TRANSLATE = translate;
    }

    public String getTRANSLATE() {
        return TRANSLATE;
    }
}
