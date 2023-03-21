package base;

import data.SpaceMarine;

import java.util.Date;
import java.util.NavigableMap;


/**
 * interface for working with our collection
 */
public interface CollectionKeeper {

    void convertToCollection();


    void clearCollection();

    int size();


    String getType();

    Date getLastInitialization();

    Date getLastSave();

    String getPath();


    void saveCollection();

    void insertToCollection(Integer key, SpaceMarine spaceMarine);

    Integer nextId();

    void removeByKey(Integer key) ;

    void removeById(Integer id) ;

    int removeByKeyIfLower(Integer key) ;

    boolean getByKey(Integer key);


    Integer getKeyById(Integer id);

    float getAverageOfHeight() ;

    String getSortedHeartCounts();

    int[] getCountingCategory();

    void sortCollection();

    Integer getKeyByMarine(SpaceMarine spaceMarine);

    int removeGreater(SpaceMarine marineToCompare) ;

    boolean replaceIfLowe(Integer key, SpaceMarine marine) ;

    NavigableMap<Integer, SpaceMarine> getMarinesCollection() ;
}
