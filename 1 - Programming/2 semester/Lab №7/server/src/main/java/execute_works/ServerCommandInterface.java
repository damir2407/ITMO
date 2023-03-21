package execute_works;

import collection_works.CollectionManager;
import commands.ServerCommand;
import messenger.Messenger;

import java.util.HashMap;

public interface ServerCommandInterface {

    void instantiateCommands();

    void putCommands();


    HashMap<String, ServerCommand> getAllCommands();

    void setMessenger(Messenger messenger);

     void setCollectionManager(CollectionManager collectionManager);
}
