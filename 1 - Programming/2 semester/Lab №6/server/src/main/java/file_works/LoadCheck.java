package file_works;

import data.SpaceMarine;
import utility.Result;
import utility.ServerValidator;

import java.util.Map;

public interface LoadCheck {

    Result<Object> check(Map<Integer, SpaceMarine> collection);

    ServerValidator getFieldsValidation() ;
}
