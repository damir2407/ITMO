package collection_works;


import com.google.gson.Gson;
import data.SpaceMarine;
import file_works.LoadCheck;
import file_works.Loader;
import file_works.Transform;
import file_works.Transformer;
import messenger.Messenger;
import server_works.ServerSendInterface;

import java.net.InetAddress;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * \
 * Class for working with our collection.
 */
public class TreeMapManager implements CollectionManager {
    private Map<Integer, SpaceMarine> marinesCollection;
    private Loader fileManager;
    private Date lastSave;
    private Date lastInitialization;
    private ServerSendInterface serverSender;
    private LoadCheck fileFieldsChecker;
    private Messenger messenger;


    public TreeMapManager(Loader fileManager, ServerSendInterface serverSender, LoadCheck fileFieldsChecker) {
        this.fileManager = fileManager;
        this.serverSender = serverSender;
        this.fileFieldsChecker = fileFieldsChecker;
        marinesCollection = new TreeMap<>();
    }

    @Override
    public void convertToCollection(InetAddress inetAddress, Integer port) {
        Transformer transform = new Transform(fileManager, new Gson(), fileFieldsChecker, inetAddress, port, serverSender);
        transform.setMessenger(messenger);
        marinesCollection = transform.convertFromJson();
        if (marinesCollection == null) marinesCollection = new TreeMap<>();
        lastInitialization = new Date();
    }


    /**
     * Clearing a collection.
     */
    @Override
    public void clearCollection() {
        marinesCollection.clear();
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


    /**
     * @return Collection's path.
     */
    @Override
    public String getPath() {
        if (fileManager.getPath() == null) return "-";
        else return fileManager.getPath();
    }

    @Override
    public void saveCollection() {
        fileManager.writeCollection(marinesCollection);
        lastSave = new Date();
    }

    /**
     * Add element to collection.
     *
     * @param key         Needed to save the key when updating.
     * @param spaceMarine The soldier to be added to the collection.
     */
    @Override
    public void insertToCollection(Integer key, SpaceMarine spaceMarine) {

        Map.Entry<Integer, SpaceMarine> spaceMarineEntry = new AbstractMap.SimpleEntry<>(key, spaceMarine);
        marinesCollection = Stream.concat(marinesCollection.entrySet().stream(), Stream.of(spaceMarineEntry))
                .collect(Collectors.toMap(k -> k.getKey(), v -> v.getValue(), (k, v) -> v, TreeMap::new));
    }

    /**
     * @return unique ID
     */
    @Override
    public Integer nextId() {
        return (int) (Math.random() * 10000);
    }

    /**
     * removes an element by key.
     *
     * @param key
     */
    @Override
    public void removeByKey(Integer key) {
        marinesCollection = marinesCollection.entrySet()
                .stream()
                .filter(x -> !x.getKey().equals(key))
                .collect(Collectors.toMap(x -> x.getKey(), y -> y.getValue(), (x, y) -> y, TreeMap::new));
    }

    /**
     * remove an element by id.
     *
     * @param id Id of element.
     */
    @Override
    public void removeById(Integer id) {
        Integer finalKey = null;
        for (Integer i : marinesCollection.keySet()) {
            if (marinesCollection.get(i).getId().equals(id)) finalKey = i;
        }
        removeByKey(finalKey);
    }

    /**
     * removes from the collection all elements whose key is less than the given one.
     *
     * @param key
     * @return number of items removed.
     */
    @Override
    public int removeByKeyIfLower(Integer key) {
        Map.Entry<Integer, SpaceMarine> entry;
        int k = 0;

        k = (int) marinesCollection.entrySet()
                .stream()
                .filter(x -> x.getKey() > key)
                .count();
        marinesCollection = marinesCollection.entrySet()
                .stream()
                .filter(x -> x.getKey() > key)
                .collect(Collectors.toMap(x -> x.getKey(), y -> y.getValue(), (x, y) -> y, TreeMap::new));
        return k;
    }

    /**
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


    /**
     * removes all elements from the collection that are greater than the specified one
     *
     * @param marineToCompare soldier to be compared
     * @return number of items removed
     */


    @Override
    public int removeGreater(SpaceMarine marineToCompare) {
        Map.Entry<Integer, SpaceMarine> entry;
        int k;
        k = (int) marinesCollection.entrySet()
                .stream()
                .filter(x -> x.getValue().getHeight() < marineToCompare.getHeight())
                .count();
        marinesCollection = marinesCollection.entrySet()
                .stream()
                .filter(x -> x.getValue().getHeight() < marineToCompare.getHeight())
                .collect(Collectors.toMap(x -> x.getKey(), y -> y.getValue(), (x, y) -> y, TreeMap::new));


        return k;
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
    public boolean replaceIfLowe(Integer key, SpaceMarine marine) {
        SpaceMarine spaceMarine = get(key);
        if (marine.compareTo(spaceMarine) < 0) {
            insertToCollection(key, marine);
            return true;
        } else return false;

    }


    @Override
    public Map<Integer, SpaceMarine> getMarinesCollection() {
        return marinesCollection;
    }
}