package client_validate;


import utility.Result;

/**
 * interface for FieldsValidation to validate fields
 */

public interface ClientValidator {

    Result<Object> finalCheckName(String name);

    Result<Object> finalCheckHealth(String health);

    Result<Object> finalCheckHeartCount(String heartCount);

    Result<Object> finalCheckHeight(String height);

    Result<Object> finalCheckCategory(String category);

    Result<Object> finalCheckX(String x);

    Result<Object> finalCheckY(String y);

    Result<Object> finalCheckChapterName(String chapterName);

    Result<Object> finalCheckChapterWorld(String chapterWorld);



}
