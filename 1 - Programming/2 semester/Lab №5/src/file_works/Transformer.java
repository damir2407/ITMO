package file_works;

import data.SpaceMarine;

import java.util.AbstractMap;
import java.util.Map;
import java.util.NavigableMap;

public interface Transformer {

    NavigableMap<Integer, SpaceMarine> convertFromJson();
}
