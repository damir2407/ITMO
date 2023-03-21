package data;

/**
 * Chapter with marines.
 */
public class Chapter {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String world; //Поле не может быть null

    public Chapter(String name, String world) {
        this.name = name;
        this.world = world;
    }

    public Chapter() {
    }

    /**
     * @return Name of the chapter.
     */
    public String getName() {
        return name;
    }
    /**
     * @return Chapter World.
     */
    public String getWorld() {
        return world;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setWorld(String world) {
        this.world = world;
    }
}



