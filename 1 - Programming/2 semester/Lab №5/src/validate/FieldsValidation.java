package validate;


import data.AstartesCategory;
import data.Chapter;
import data.Coordinates;
import data.SpaceMarine;
import messenger.Messenger;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;

/**
 * class for check marine's fields
 */

public class FieldsValidation implements Validator {
    private Messenger messenger;


    public FieldsValidation(Messenger messenger) {
        this.messenger = messenger;
    }

    /**
     * @param coordinates to check
     * @return coordinates
     */
    @Override
    public Result finalCheckCoordinates(Coordinates coordinates) {
        if (coordinates == null) return new Result().error(messenger.incorrectCoordinatesMessage());
        return new Result().ok(coordinates);
    }

    /**
     * @param x to check
     * @return x coordinate
     */
    @Override
    public Result finalCheckX(String x) {
        Float newX;
        try {
            newX = Float.parseFloat(x);
        } catch (NumberFormatException e) {
            return new Result().error(messenger.incorrectXCoordinateMessage());
        }
        if (!(newX != null && newX <= 610))
            return new Result().error(messenger.incorrectXCoordinateMessage());
        return new Result().ok(newX);
    }

    /**
     * @param y to check
     * @return y coordinate
     */
    @Override
    public Result finalCheckY(String y) {
        Double newY = null;
        try {
            newY = Double.parseDouble(y);
        } catch (NumberFormatException e) {
            return new Result().error(messenger.incorrectYCoordinateMessage());
        }
        if (newY == null)
            return new Result().error(messenger.incorrectYCoordinateMessage());
        return new Result().ok(newY);
    }

    /**
     * @param chapterName to check
     * @return chapter name
     */
    @Override
    public Result finalCheckChapterName(String chapterName) {
        if (chapterName == null || chapterName.isEmpty())
            return new Result().error(messenger.incorrectChapterNameMessage());
        return new Result().ok(chapterName);
    }

    /**
     * @param chapterWorld to check
     * @return chapter world
     */
    @Override
    public Result finalCheckChapterWorld(String chapterWorld) {
        if (chapterWorld == null)
            return new Result().error(messenger.incorrectChapterWorldMessage());
        return new Result().ok(chapterWorld);
    }

    /**
     * @param chapter to check
     * @return chapter
     */
    @Override
    public Result finalCheckChapter(Chapter chapter) {
        try {
        } catch (NullPointerException e) {
            chapter = null;
        }
        return new Result().ok(chapter);
    }

    /**
     * @param name to check
     * @return name
     */
    @Override
    public Result finalCheckName(String name) {
        if (name == null || name.isEmpty())
            return new Result().error(messenger.incorrectNameMessage());
        return new Result().ok(name);
    }

    /**
     * @param date to check
     * @return date
     */
    @Override
    public Result finalCheckCreationDate(Date date) {
        if (date == null)
            return new Result().error(messenger.incorrectCreationDateMessage());
        return new Result().ok(date);
    }

    /**
     * @param health to check
     * @return health
     */
    @Override
    public Result finalCheckHealth(String health) {
        Float newHealth;
        try {
            newHealth = Float.parseFloat(health);
        } catch (NumberFormatException e) {
            return new Result().error(messenger.incorrectHealthMessage());
        }
        if (newHealth == null || newHealth <= 0)
            return new Result().error(messenger.incorrectHealthMessage());
        return new Result().ok(newHealth);
    }

    /**
     * @param id to check
     * @return id
     */
    @Override
    public Result finalCheckId(Integer id) {
        if (id == null || id <= 0)
            return new Result().error(messenger.incorrectIdMessage());
        return new Result().ok(id);
    }

    /**
     * @param collection to check
     * @return collection
     */
    @Override
    public Result finalCheckIdUniqueness(NavigableMap<Integer, SpaceMarine> collection) {
        Map<Integer, Boolean> finalMap = new HashMap<>();
        for (Integer i : collection.keySet()) {
            finalMap.put(collection.get(i).getId(), false);
        }
        if (finalMap.size() != collection.size()) {
            return new Result().error(messenger.incorrectIdUniquenessMessage());
        }
        return new Result().ok(collection);
    }

    /**
     * @param heartCount to check
     * @return heartCount
     */
    @Override
    public Result finalCheckHeartCount(String heartCount) {
        Integer newHeartCount;
        try {
            newHeartCount = Integer.parseInt(heartCount);
        } catch (NumberFormatException e) {
            return new Result().error(messenger.incorrectHeartCountMessage());
        }
        if (newHeartCount == null || newHeartCount <= 0 || newHeartCount > 3)
            return new Result().error(messenger.incorrectHeartCountMessage());
        return new Result().ok(newHeartCount);
    }

    /**
     * @param height to check
     * @return height
     */
    @Override
    public Result finalCheckHeight(String height) {
        float newHeight;
        try {
            newHeight = Float.parseFloat(height);
        } catch (NumberFormatException e) {
            return new Result().error(messenger.incorrectHeightMessage());
        }
        return new Result().ok(newHeight);
    }

    /**
     * @param category to check
     * @return category
     */
    @Override
    public Result finalCheckCategory(String category) {
        AstartesCategory newCategory;
        try {
            newCategory = AstartesCategory.valueOf(category.toUpperCase());
            if (newCategory == null)
                return new Result().error(messenger.incorrectCategoryMessage());
        } catch (IllegalArgumentException e) {
            return new Result().error(messenger.categoryDoesNotExist());
        }
        return new Result().ok(newCategory);
    }


}
