package data;

import java.io.Serializable;
import java.util.*;

/**
 * Main character. Is stored in the collection.
 */

public class SpaceMarine implements Serializable, Comparable<SpaceMarine> {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Float health; //Поле не может быть null, Значение поля должно быть больше 0
    private Integer heartCount; //Поле не может быть null, Значение поля должно быть больше 0, Максимальное значение поля: 3
    private float height;
    private AstartesCategory category; //Поле не может быть null
    private Chapter chapter; //Поле может быть null
    private String userLogin;


    public SpaceMarine(Integer id, String name, Coordinates coordinates, Date creationDate, Float health, Integer heartCount, float height, AstartesCategory category, Chapter chapter, String userLogin) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.health = health;
        this.heartCount = heartCount;
        this.height = height;
        this.category = category;
        this.chapter = chapter;
        this.userLogin = userLogin;
    }

    public SpaceMarine() {
    }

    /**
     * @return ID of the marine.
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return Name of the marine.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Coordinates of the marine.
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * @return Creation date of the marine.
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @return Health of the marine.
     */
    public Float getHealth() {
        return health;
    }

    /**
     * @return Heart Count of the marine.
     */
    public Integer getHeartCount() {
        return heartCount;
    }

    /**
     * @return Height of the marine.
     */
    public float getHeight() {
        return height;
    }

    /**
     * @return Category of the marine.
     */
    public AstartesCategory getCategory() {
        return category;
    }

    /**
     * @return Chapter of the marine.
     */
    public Chapter getChapter() {
        return chapter;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setHealth(Float health) {
        this.health = health;
    }

    public void setHeartCount(Integer heartCount) {
        this.heartCount = heartCount;
    }

    public void setCategory(AstartesCategory category) {
        this.category = category;
    }

    @Override
    public int compareTo(SpaceMarine o) {
        if (getHeight() > o.getHeight()) return 1;
        if (getHeight() < o.getHeight()) return -1;
        return 0;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }
}
