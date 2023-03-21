package file_works;

import data.SpaceMarine;
import utility.Error;
import utility.Result;
import utility.ServerValidator;
import utility.Success;

import java.util.Map;

/**
 * To check fields from file
 */
public class LoadFieldsChecker implements LoadCheck {

    private ServerValidator fieldsValidation;

    public LoadFieldsChecker(ServerValidator fieldsValidation) {
        this.fieldsValidation = fieldsValidation;
    }

    /**
     * @param collection The collection with which the work is taking place
     * @return status
     */

    @Override
    public Result<Object> check(Map<Integer, SpaceMarine> collection) {
        Result<Object> result;
        for (Integer i : collection.keySet()) {
            result = fieldsValidation.finalCheckId(collection.get(i).getId());
            if (result instanceof Error)
                return result;
            result = fieldsValidation.finalCheckIdUniqueness(collection);
            if (result instanceof Error)
                return result;
            result = fieldsValidation.finalCheckName(collection.get(i).getName());
            if (result instanceof Error)
                return result;
            result = fieldsValidation.finalCheckCoordinates(collection.get(i).getCoordinates());
            if (result instanceof Error)
                return result;
            result = fieldsValidation.finalCheckX(String.valueOf(collection.get(i).getCoordinates().getX()));
            if (result instanceof Error)
                return result;
            result = fieldsValidation.finalCheckY(String.valueOf(collection.get(i).getCoordinates().getY()));
            if (result instanceof Error)
                return result;
            result = fieldsValidation.finalCheckCreationDate(collection.get(i).getCreationDate());
            if (result instanceof Error)
                return result;
            result = fieldsValidation.finalCheckHealth(String.valueOf(collection.get(i).getHealth()));
            if (result instanceof Error)
                return result;
            result = fieldsValidation.finalCheckHeartCount(String.valueOf(collection.get(i).getHeartCount()));
            if (result instanceof Error)
                return result;
            result = fieldsValidation.finalCheckCategory(String.valueOf(collection.get(i).getCategory()));
            if (result instanceof Error)
                return result;
            result = fieldsValidation.finalCheckChapter(collection.get(i).getChapter());
            if (result instanceof Success) {
                result = fieldsValidation.finalCheckChapterName(collection.get(i).getChapter().getName());
                if (result instanceof Error)
                    return result;
                result = fieldsValidation.finalCheckChapterWorld(collection.get(i).getChapter().getWorld());
                if (result instanceof Error)
                    return result;
            }
        }
        return new Success<>(null);
    }

    public ServerValidator getFieldsValidation() {
        return fieldsValidation;
    }
}
