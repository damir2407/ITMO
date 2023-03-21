package utility;

import data.Chapter;
import data.Coordinates;
import data.SpaceMarine;
import messenger.Messenger;

import java.util.Date;
import java.util.Map;

/**
 * interface for FieldsValidation to validate fields
 */

public interface ServerValidator {

    Result<Object> finalCheckName(String name);

    Result<Object> finalCheckCreationDate(Date date);

    Result<Object> finalCheckHealth(String health);

    Result<Object> finalCheckId(Integer id);

    Result<Object> finalCheckIdUniqueness(Map<Integer, SpaceMarine> collection);

    Result<Object> finalCheckHeartCount(String heartCount);

    Result<Object> finalCheckHeight(String height);

    Result<Object> finalCheckCategory(String category);

    Result<Object> finalCheckCoordinates(Coordinates coordinates);

    Result<Object> finalCheckX(String x);

    Result<Object> finalCheckY(String y);

    Result<Object> finalCheckChapterName(String chapterName);

    Result<Object> finalCheckChapterWorld(String chapterWorld);

    Result<Object> finalCheckChapter(Chapter chapter);

     void setMessenger(Messenger messenger);

}
