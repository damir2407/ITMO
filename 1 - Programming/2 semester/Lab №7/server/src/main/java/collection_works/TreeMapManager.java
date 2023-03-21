package collection_works;


import data.SpaceMarine;
import file_works.LoadCheck;
import file_works.Loader;
import file_works.Transform;
import file_works.Transformer;
import messenger.Messenger;
import server_works.DataBase;
import server_works.ServerSender;
import utility.Error;
import utility.Result;
import utility.Success;

import java.net.InetAddress;
import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

/**
 * \
 * Class for working with our collection.
 */
public class TreeMapManager implements CollectionManager {
    private Map<Integer, SpaceMarine> marinesCollection;
    private Loader fileManager;
    private Date lastSave;
    private Date lastInitialization;
    private ServerSender serverSender;
    private LoadCheck fileFieldsChecker;
    private Messenger messenger;
    private DataBase dataBase;
    private static final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);


    public TreeMapManager(Loader fileManager, ServerSender serverSender, LoadCheck fileFieldsChecker, DataBase dataBase) {
        this.fileManager = fileManager;
        this.serverSender = serverSender;
        this.fileFieldsChecker = fileFieldsChecker;
        marinesCollection = new TreeMap<>();
        this.dataBase = dataBase;
    }

    @Override
    public void convertToCollection(InetAddress inetAddress, Integer port) {
        Transformer transform = new Transform(fileManager, fileFieldsChecker, inetAddress, port, serverSender);
        transform.setMessenger(messenger);
        marinesCollection = transform.convertFromJson();
        if (marinesCollection == null) marinesCollection = new TreeMap<>();
        lastInitialization = new Date();
    }


    /**
     * @return size of collection
     */
    @Override
    public int size() {
        return marinesCollection.size();
    }

    /**
     * Get collection from File Manager
     */
    public LoadCheck getFileFieldsChecker() {
        return fileFieldsChecker;
    }

    /**
     * @return Name of the collection's type.
     */
    @Override
    public String getType() {
        return marinesCollection.getClass().getName();
    }

    @Override
    public Date getLastInitialization() {
        return lastInitialization;
    }

    @Override
    public Date getLastSave() {
        return lastSave;
    }


    @Override
    public void saveCollection() {
        fileManager.writeCollection(marinesCollection);
        lastSave = new Date();
    }


    /**
     * remove an element by id.
     *
     * @param id Id of element.
     */

    /**
     * removes from the collection all elements whose key is less than the given one.
     *
     * @param key
     * @param key of Soldier
     * @return returns the soldier by key
     */
    @Override
    public boolean getByKey(Integer key) {
        try {
            marinesCollection.entrySet()
                    .stream()
                    .filter(x -> x.getKey().equals(key));
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    /**
     * @param id of Soldier
     * @return returns key by id
     */
    @Override
    public Integer getKeyById(Integer id) {
        sortCollection();
        try {
            return marinesCollection.entrySet()
                    .stream()
                    .filter(x -> x.getValue().getId().equals(id))
                    .map(x -> x.getKey())
                    .findFirst()
                    .get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }


    /**
     * @return the average value of the height field for all elements in the collection
     */
    @Override
    public float getAverageOfHeight() {
        Optional<Float> list = marinesCollection.entrySet()
                .stream()
                .map(x -> x.getValue().getHeight())
                .reduce((left, right) -> left + right);
        return list.get() / marinesCollection.size();
    }

    /**
     * @return string of our heartCounts and id's
     */
    @Override
    public String getSortedHeartCounts() {
        readWriteLock.writeLock().lock();
        readWriteLock.readLock().lock();
        String s = "";
        List<Integer> idList = marinesCollection.entrySet()
                .stream()
                .map(x -> x.getValue().getId())
                .collect(Collectors.toList());
        List<Integer> heartCountList = marinesCollection.entrySet()
                .stream()
                .map(x -> x.getValue().getHeartCount())
                .collect(Collectors.toList());
        for (Integer i = 0; i < idList.size(); i++) {
            s += idList.get(i) + " " + heartCountList.get(i) + "\n";
        }
        readWriteLock.writeLock().unlock();
        readWriteLock.readLock().unlock();
        return s;
    }

    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;
    }

    /**
     * @return array of collection category count
     */
    @Override
    public int[] getCountingCategory() {
        int[] array = new int[5];
        readWriteLock.writeLock().lock();
        readWriteLock.readLock().lock();
        array[0] = (int) marinesCollection.entrySet()
                .stream()
                .filter(x -> x.getValue().getCategory().name().equals("SCOUT"))
                .count();
        array[1] = (int) marinesCollection.entrySet()
                .stream()
                .filter(x -> x.getValue().getCategory().name().equals("DREADNOUGHT"))
                .count();
        array[2] = (int) marinesCollection.entrySet()
                .stream()
                .filter(x -> x.getValue().getCategory().name().equals("AGGRESSOR"))
                .count();
        array[3] = (int) marinesCollection.entrySet()
                .stream()
                .filter(x -> x.getValue().getCategory().name().equals("SUPPRESSOR"))
                .count();
        array[4] = (int) marinesCollection.entrySet()
                .stream()
                .filter(x -> x.getValue().getCategory().name().equals("CHAPLAIN"))
                .count();
        readWriteLock.writeLock().unlock();
        readWriteLock.readLock().unlock();
        return (array);

    }

    /**
     * sorts the collection by heartCount
     */
    @Override
    public void sortCollection() {

        List<SpaceMarine> list = new ArrayList<>(marinesCollection.values());

        list.stream()
                .sorted()
                .collect(Collectors.toList());

        marinesCollection = list
                .stream()
                .collect(Collectors.toMap(x -> getKeyByMarine(x), x -> x, (x, y) -> y, TreeMap::new));


    }

    @Override
    public Integer getKeyByMarine(SpaceMarine spaceMarine) {
        for (Integer i : marinesCollection.keySet()) {
            if (get(i).equals(spaceMarine)) return i;
        }
        return null;
    }


    public Result<Object> checkBeforeDelete(Integer key, String login) {
        if (!marinesCollection.get(key).getUserLogin().equals(login)) {
            return new Error(messenger.notEnoughRights());
        } else return new Success<>(null);
    }


    public SpaceMarine get(Integer key) {
        return marinesCollection.entrySet()
                .stream()
                .filter(x -> x.getKey().equals(key))
                .findFirst()
                .get()
                .getValue();
    }


    @Override
    public void updateCollection() {
        this.marinesCollection = dataBase.getInformation();
    }

    @Override
    public Map<Integer, SpaceMarine> getMarinesCollection() {
        return marinesCollection;
    }
}