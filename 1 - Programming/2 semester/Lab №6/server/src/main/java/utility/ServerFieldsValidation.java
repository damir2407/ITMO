package utility;


import data.AstartesCategory;
import data.Chapter;
import data.Coordinates;
import data.SpaceMarine;
import messenger.Messenger;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * class for check marine's fields
 */

public class ServerFieldsValidation implements ServerValidator {
    private Messenger messenger;

    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;
    }

    /**
     * @param coordinates to check
     * @return coordinates
     */
    @Override
    public Result<Object> finalCheckCoordinates(Coordinates coordinates) {
        if (coordinates == null) return new Error(messenger.incorrectCoordinatesMessage());
        return new Success<Coordinates>(coordinates);
    }

    /**
     * @param x to check
     * @return x coordinate
     */
    @Override
    public Result<Object> finalCheckX(String x) {
        Float newX;
        try {
            newX = Float.parseFloat(x);
        } catch (NumberFormatException e) {
            return new Error(messenger.incorrectXCoordinateMessage());
        }
        if (!(newX != null && newX <= 610))
            return new Error(messenger.incorrectXCoordinateMessage());
        return new Success<Float>(newX);
    }

    /**
     * @param y to check
     * @return y coordinate
     */
    @Override
    public Result<Object> finalCheckY(String y) {
        Double newY = null;
        try {
            newY = Double.parseDouble(y);
        } catch (NumberFormatException e) {
            return new Error(messenger.incorrectYCoordinateMessage());
        }
        if (newY == null)
            return new Error(messenger.incorrectYCoordinateMessage());
        return new Success<Double>(newY);
    }

    /**
     * @param chapterName to check
     * @return chapter name
     */
    @Override
    public Result<Object> finalCheckChapterName(String chapterName) {
        if (chapterName == null || chapterName.isEmpty())
            return new Error(messenger.incorrectChapterNameMessage());
        return new Success<String>(chapterName);
    }

    /**
     * @param chapterWorld to check
     * @return chapter world
     */
    @Override
    public Result<Object> finalCheckChapterWorld(String chapterWorld) {
        if (chapterWorld == null)
            return new Error(messenger.incorrectChapterWorldMessage());
        return new Success<String>(chapterWorld);
    }

    /**
     * @param chapter to check
     * @return chapter
     */
    @Override
    public Result<Object> finalCheckChapter(Chapter chapter) {
        try {
        } catch (NullPointerException e) {
            chapter = null;
        }
        return new Success<Chapter>(chapter);
    }

    /**
     * @param name to check
     * @return name
     */
    @Override
    public Result<Object> finalCheckName(String name) {
        if (name == null || name.isEmpty())
            return new Error(messenger.incorrectNameMessage());
        return new Success<String>(name);
    }

    /**
     * @param date to check
     * @return date
     */
    @Override
    public Result<Object> finalCheckCreationDate(Date date) {
        if (date == null)
            return new Error(messenger.incorrectCreationDateMessage());
        return new Success<Date>(date);
    }

    /**
     * @param health to check
     * @return health
     */
    @Override
    public Result<Object> finalCheckHealth(String health) {
        Float newHealth;
        try {
            newHealth = Float.parseFloat(health);
        } catch (NumberFormatException e) {
            return new Error(messenger.incorrectHealthMessage());
        }
        if (newHealth == null || newHealth <= 0)
            return new Error(messenger.incorrectHealthMessage());
        return new Success<Float>(newHealth);
    }

    /**
     * @param id to check
     * @return id
     */
    @Override
    public Result<Object> finalCheckId(Integer id) {
        if (id == null || id <= 0)
            return new Error(messenger.incorrectIdMessage());
        return new Success<Integer>(id);
    }

    /**
     * @param collection to check
     * @return collection
     */
    @Override
    public Result<Object> finalCheckIdUniqueness(Map<Integer, SpaceMarine> collection) {
        Map<Integer, Boolean> finalMap = new HashMap<>();
        for (Integer i : collection.keySet()) {
            finalMap.put(collection.get(i).getId(), false);
        }
        if (finalMap.size() != collection.size()) {
            return new Error(messenger.incorrectIdUniquenessMessage());
        }
        return new Success<Map>(collection);
    }

    /**
     * @param heartCount to check
     * @return heartCount
     */
    @Override
    public Result<Object> finalCheckHeartCount(String heartCount) {
        Integer newHeartCount;
        try {
            newHeartCount = Integer.parseInt(heartCount);
        } catch (NumberFormatException e) {
            return new Error(messenger.incorrectHeartCountMessage());
        }
        if (newHeartCount == null || newHeartCount <= 0 || newHeartCount > 3)
            return new Error(messenger.incorrectHeartCountMessage());
        return new Success<Integer>(newHeartCount);
    }

    /**
     * @param height to check
     * @return height
     */
    @Override
    public Result<Object> finalCheckHeight(String height) {
        float newHeight;
        try {
            newHeight = Float.parseFloat(height);
        } catch (NumberFormatException e) {
            return new Error(messenger.incorrectHeightMessage());
        }
        return new Success<Float>(newHeight);
    }

    /**
     * @param category to check
     * @return category
     */
    @Override
    public Result<Object> finalCheckCategory(String category) {
        AstartesCategory newCategory;
        try {
            newCategory = AstartesCategory.valueOf(category.toUpperCase());
            if (newCategory == null)
                return new Error(messenger.incorrectCategoryMessage());
        } catch (IllegalArgumentException e) {
            return new Error(messenger.categoryDoesNotExist());
        }
        return new Success<AstartesCategory>(newCategory);
    }


}
