package collection_works;

import data.SpaceMarine;
import file_works.LoadCheck;
import messenger.Messenger;
import utility.Result;

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



    int size();

    void updateCollection();

    String getType();

    Result<Object> checkBeforeDelete(Integer key, String login);

    Date getLastInitialization();

    Date getLastSave();


    void saveCollection();


    boolean getByKey(Integer key);


    Integer getKeyById(Integer id);

    float getAverageOfHeight();

    String getSortedHeartCounts();

    int[] getCountingCategory();

    void sortCollection();

    Integer getKeyByMarine(SpaceMarine spaceMarine);



    Map<Integer, SpaceMarine> getMarinesCollection();
}
