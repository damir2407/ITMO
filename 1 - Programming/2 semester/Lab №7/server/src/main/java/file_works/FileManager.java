package file_works;

import data.*;
import server_works.DataBase;
import java.util.*;

/**
 * Operates the file for saving/loading collection.
 */
public class FileManager implements Loader {

    private DataBase dataBase;


    public FileManager(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public Map<Integer, SpaceMarine> load() {
        Map<Integer, SpaceMarine> marines = new TreeMap<>();

        marines = dataBase.getInformation();
        return marines;
    }

    /**
     * Writes collection to a file.
     *
     * @param collection Collection to write.
     */
    @Override
    public void writeCollection(Map<Integer, SpaceMarine> collection) {
        dataBase.addInformation(collection);
    }

}

