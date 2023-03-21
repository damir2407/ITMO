package validate;

import data.Chapter;
import data.Coordinates;
import data.SpaceMarine;

import java.util.Date;
import java.util.NavigableMap;

/**
 * interface for FieldsValidation to validate fields
 */

public interface Validator {

    ResultKeeper finalCheckName(String name);

    ResultKeeper finalCheckCreationDate(Date date);

    ResultKeeper finalCheckHealth(String health);

    ResultKeeper finalCheckId(Integer id);

    ResultKeeper finalCheckIdUniqueness(NavigableMap<Integer, SpaceMarine> collection);

    ResultKeeper finalCheckHeartCount(String heartCount);

    ResultKeeper finalCheckHeight(String height);

    ResultKeeper finalCheckCategory(String category);

    ResultKeeper finalCheckCoordinates(Coordinates coordinates);

    ResultKeeper finalCheckX(String x);

    ResultKeeper finalCheckY(String y);

    ResultKeeper finalCheckChapterName(String chapterName);

    ResultKeeper finalCheckChapterWorld(String chapterWorld);

    ResultKeeper finalCheckChapter(Chapter chapter);


}
