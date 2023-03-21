package collection_works;

import data.SpaceMarine;
import file_works.LoadCheck;
import messenger.Messenger;

import java.net.InetAddress;
import java.util.Date;
import java.util.Map;


/**
 * interface for working with our collection
 */
public interface CollectionManager {

    void setMessenger(Messenger messenger);


    LoadCheck getFileFieldsChecker();
    void convertToCollection(InetAddress inetAddress, Integer port);


    void clearCollection();

    int size();


    String getType();

    Date getLastInitialization();

    Date getLastSave();

    String getPath();


    void saveCollection();

    void insertToCollection(Integer key, SpaceMarine spaceMarine);

    Integer nextId();

    void removeByKey(Integer key);

    void removeById(Integer id);

    int removeByKeyIfLower(Integer key);

    boolean getByKey(Integer key);


    Integer getKeyById(Integer id);

    float getAverageOfHeight();

    String getSortedHeartCounts();

    int[] getCountingCategory();

    void sortCollection();

    Integer getKeyByMarine(SpaceMarine spaceMarine);

    int removeGreater(SpaceMarine marineToCompare);

    boolean replaceIfLowe(Integer key, SpaceMarine marine);

    Map<Integer, SpaceMarine> getMarinesCollection();
}
