package file_works;

import data.SpaceMarine;
import messenger.Messenger;

import java.util.AbstractMap;
import java.util.Map;
import java.util.NavigableMap;

public interface Transformer {

    Map<Integer, SpaceMarine> convertFromJson();

     void setMessenger(Messenger messenger);
}
