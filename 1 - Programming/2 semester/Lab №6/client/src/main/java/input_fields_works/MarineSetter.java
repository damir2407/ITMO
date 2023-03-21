package input_fields_works;

import ask_works.PollInterface;
import data.AstartesCategory;
import data.Chapter;
import data.Coordinates;
import data.SpaceMarine;


import java.util.Date;

public class MarineSetter implements Setter {

    private PollInterface poll;

    public MarineSetter(PollInterface poll) {
        this.poll = poll;
    }

    @Override
    public void setFieldId(SpaceMarine spaceMarine, Integer id) {
        spaceMarine.setId(id);
    }

    @Override
    public void setCoordinates(SpaceMarine spaceMarine, Coordinates coordinates) {
        spaceMarine.setCoordinates(coordinates);
    }

    @Override
    public void setChapter(SpaceMarine spaceMarine, Chapter chapter) {
        spaceMarine.setChapter(chapter);
    }

    @Override
    public void setCreationDate(SpaceMarine spaceMarine, Date date) {
        spaceMarine.setCreationDate(date);
    }

    @Override
    public boolean setFieldName(SpaceMarine spaceMarine) {
        String name = poll.claimName();
        if (!name.isEmpty()) {
            spaceMarine.setName(name);
            return true;
        } else return false;
    }

    /**
     * set x coordinate
     *
     * @param coordinates to set x coordinate
     */

    @Override
    public boolean setFieldXCoordinate(Coordinates coordinates) {
        Float x = poll.claimXCoordinate();
        if (x != null) {
            coordinates.setX(x);
            return true;
        } else return false;
    }

    /**
     * set y coordinate
     *
     * @param coordinates to set y coordinate
     */

    @Override
    public boolean setFieldYCoordinate(Coordinates coordinates) {
        Double y = poll.claimYCoordinate();
        if (y != null) {
            coordinates.setY(y);
            return true;
        } else return false;
    }

    /**
     * set marine health
     *
     * @param spaceMarine to set health
     */
    @Override
    public boolean setFieldHealth(SpaceMarine spaceMarine) {
        Float health = poll.claimHealth();
        if (health != null) {
            spaceMarine.setHealth(health);
            return true;
        } else return false;
    }

    /**
     * set marine heartCount
     *
     * @param spaceMarine to set heartCount
     */

    @Override
    public boolean setFieldHeartCount(SpaceMarine spaceMarine) {
        Integer heartCount = poll.claimHeartCount();
        if (heartCount != null) {
            spaceMarine.setHeartCount(heartCount);
            return true;
        } else return false;
    }

    /**
     * set marineHeight
     *
     * @param spaceMarine to set height
     */

    @Override
    public boolean setFieldHeight(SpaceMarine spaceMarine) {
        float height = poll.claimHeight();
        if (height != 0) {
            spaceMarine.setHeight(height);
            return true;
        } else return false;
    }

    /**
     * set marine category
     *
     * @param spaceMarine to set category
     */

    @Override
    public boolean setFieldCategory(SpaceMarine spaceMarine) {
        AstartesCategory astartesCategory = poll.claimCategory();
        if (astartesCategory != null) {
            spaceMarine.setCategory(astartesCategory);
            return true;
        } else return false;
    }

    /**
     * set chapter name
     *
     * @param chapter to set name
     */

    @Override
    public boolean setFieldChapterName(Chapter chapter) {
        String chapterName = poll.claimChapterName();
        if (!chapterName.isEmpty()) {
            chapter.setName(chapterName);
            return true;
        } else return false;
    }

    /**
     * set chapter world
     *
     * @param chapter to set world
     */

    @Override
    public boolean setFieldChapterWorld(Chapter chapter) {
        String chapterWorld = poll.claimChapterWorld();
        chapter.setWorld(chapterWorld);
        return true;
    }
}