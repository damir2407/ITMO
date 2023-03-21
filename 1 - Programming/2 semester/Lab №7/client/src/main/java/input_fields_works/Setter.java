package input_fields_works;

import data.Chapter;
import data.Coordinates;
import data.SpaceMarine;

import java.util.Date;

public interface Setter {

    boolean setFieldName(SpaceMarine spaceMarine);

    boolean setFieldXCoordinate(Coordinates coordinates);

    boolean setFieldYCoordinate(Coordinates coordinates);

    boolean setFieldHealth(SpaceMarine spaceMarine);

    boolean setFieldHeartCount(SpaceMarine spaceMarine);

    boolean setFieldHeight(SpaceMarine spaceMarine);

    boolean setFieldCategory(SpaceMarine spaceMarine);

    boolean setFieldChapterName(Chapter chapter);

    boolean setFieldChapterWorld(Chapter chapter);

    void setFieldId(SpaceMarine spaceMarine, Integer id);

    void setCoordinates(SpaceMarine spaceMarine, Coordinates coordinates);

    void setChapter(SpaceMarine spaceMarine, Chapter chapter);

    void setCreationDate(SpaceMarine spaceMarine, Date date);

}
