package input_fields_works;

import data.Chapter;
import data.Coordinates;
import data.SpaceMarine;

/**
 * interface for CommandRepeater for AskCommand's
 */
public interface Repeater {
    SpaceMarine repeatFields(Integer integer);

    void repeatSetFieldName(SpaceMarine spaceMarine);

    void repeatSetFieldXCoordinate(Coordinates coordinates);

    void repeatSetFieldYCoordinate(Coordinates coordinates);

    void repeatSetFieldHealth(SpaceMarine spaceMarine);

    void repeatSetFieldHeartCount(SpaceMarine spaceMarine);

    void repeatSetFieldHeight(SpaceMarine spaceMarine);

    void repeatSetFieldCategory(SpaceMarine spaceMarine);

    void repeatSetFieldChapterName(Chapter chapter);

    void repeatSetFieldChapterWorld(Chapter chapter);
}
