package base;


import messenger.Messenger;
import messenger.PrintKeeper;

import java.util.HashMap;

/**
 * interface for command manager
 */

public interface CommandKeeper {


    boolean clear(String argument);


    boolean exit(String argument);


    boolean help(String argument);


    boolean info(String argument);


    boolean show(String argument);


    boolean save(String argument);


    boolean insert(String argument);


    boolean removeByKey(String argument);


    boolean updateById(String argument);

    boolean averageOfHeight(String argument);


    boolean removeLower(String argument);

    boolean printFieldHeart(String argument);


    boolean groupCountingByCategory(String argument);

    HashMap<String, Converter<String, Boolean>> getAllCommands();

    boolean removeGreater(String argument);

    boolean replaceIfLowe(String argument);

    boolean executeScript(String argument);

    void createCommands(CollectionKeeper collectionManager, Messenger messenger, PrintKeeper printMachine);

    public void putCommands();
}
