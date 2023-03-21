package base;


import data.SpaceMarine;
import file_works.FileKeeper;
import file_works.Transformer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * \
 * Class for working with our collection.
 */
public class CollectionManager implements CollectionKeeper {
    private NavigableMap<Integer, SpaceMarine> marinesCollection;
    private FileKeeper fileManager;
    private Date lastSave;
    private Date lastInitialization;
    private Transformer transform;


    public CollectionManager(FileKeeper fileManager, Transformer transform) {
        this.fileManager = fileManager;
        this.transform = transform;
        marinesCollection = new TreeMap<>();
    }

    @Override
    public void convertToCollection() {
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
/*        if (marinesCollection.get(key) == null)
            return null;
        else return marinesCollection.get(key);*/
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
/*        String str = "";
        for (Integer i : marinesCollection.keySet()) {
            str = str + "id: " + marinesCollection.get(i).getId() + ", heartCount: " + marinesCollection.get(i).getHeartCount() + "\n";
        }
        return str;*/
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
        //Компаратор для херткаунта
    }


    /**
     * @return array of collection category count
     */
    @Override
    public int[] getCountingCategory() {
        int f1 = 0;
        int f2 = 0;
        int f3 = 0;
        int f4 = 0;
        int f5 = 0;
        int[] array = new int[5];
        for (Integer i : marinesCollection.keySet()) {
            if (marinesCollection.get(i).getCategory().name().equals("SCOUT")) {
                f1++;
            } else if (marinesCollection.get(i).getCategory().name().equals("DREADNOUGHT")) {
                f2++;
            } else if (marinesCollection.get(i).getCategory().name().equals("AGGRESSOR")) {
                f3++;
            } else if (marinesCollection.get(i).getCategory().name().equals("SUPPRESSOR")) {
                f4++;
            } else if (marinesCollection.get(i).getCategory().name().equals("CHAPLAIN")) {
                f5++;
            }
            for (int j = 0; j < 5; j++) {
                array[0] = f1;
                array[1] = f2;
                array[2] = f3;
                array[3] = f4;
                array[4] = f5;
            }
        }
        return (array);
    }

    /**
     * sorts the collection by heartCount
     */
    @Override
    public void sortCollection() {

        List<SpaceMarine> list = new ArrayList<>(marinesCollection.values());
        list.sort(new Comparator<SpaceMarine>() {
            @Override
            public int compare(SpaceMarine o1, SpaceMarine o2) {
                if (o1.getHeight() == o2.getHeight()) return 0;
                else if (o1.getHeight() > o2.getHeight()) return 1;
                else return -1;
            }
        });
        NavigableMap<Integer, SpaceMarine> treeMap = new TreeMap<>();
        for (SpaceMarine spaceMarine : list) {
            treeMap.put(getKeyByMarine(spaceMarine), spaceMarine);
        }
        this.marinesCollection = treeMap;


    }

    @Override
    public Integer getKeyByMarine(SpaceMarine spaceMarine) {
        for (Integer i : marinesCollection.keySet()) {
            if (marinesCollection.get(i).equals(spaceMarine)) return i;
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
        int k = 0;
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

    /**
     * @param key    key of marine.
     * @param marine soldier to be compared
     * @return replacement status (true or false)
     */
    @Override
    public boolean replaceIfLowe(Integer key, SpaceMarine marine) {
        if (marine.getHeartCount() < marinesCollection.get(key).getHeartCount()) {
            marinesCollection.replace(key, marine);
            return true;
        }
        return false;
    }

    @Override
    public NavigableMap<Integer, SpaceMarine> getMarinesCollection() {
        return marinesCollection;
    }
}