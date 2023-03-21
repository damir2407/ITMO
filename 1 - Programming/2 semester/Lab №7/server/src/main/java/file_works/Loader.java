package file_works;

import data.SpaceMarine;

import java.io.FileNotFoundException;
import java.util.AbstractMap;
import java.util.Map;
import java.util.NavigableMap;


/**
 * interface for FileManager
 */
public interface Loader {

     Map<Integer, SpaceMarine> load() ;

    void writeCollection(Map<Integer, SpaceMarine> collection);

}
