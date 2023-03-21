package file_works;

import data.SpaceMarine;

import java.io.FileNotFoundException;
import java.util.NavigableMap;


/**
 * interface for FileManager
 */
public interface FileKeeper {

    String load() throws FileNotFoundException, SecurityException;

    void writeCollection(NavigableMap<Integer, SpaceMarine> collection);


    String getPath();

}
