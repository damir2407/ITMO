public interface AliveMoves {
    void openDoors(Walls.Door door, Place place);

    void pace();

    void toKneel();

    void lookIn(Place place);

    void getOut(Place place);

    void resent(String reason);

    void wantBe(FoldThings foldThings, FoldThings foldThings2);
}
