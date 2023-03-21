package client_validate;


import  data.AstartesCategory;
import messenger.Messenger;
import utility.Error;
import utility.Result;
import utility.Success;

/**
 * class for check marine's fields
 */

public class ClientFieldsValidation implements ClientValidator {
    private Messenger messenger;


    public ClientFieldsValidation(Messenger messenger) {
        this.messenger = messenger;
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
