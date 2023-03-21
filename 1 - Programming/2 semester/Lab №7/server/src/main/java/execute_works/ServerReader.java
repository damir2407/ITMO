package execute_works;

import collection_works.CollectionManager;
import commands.SaveCommand;
import commands.ServerCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class ServerReader implements ServerReadInterface {
    private CollectionManager collectionManager;
    private static final Logger logger = LogManager.getLogger(ServerReader.class);

    public ServerReader() {
    }

    @Override
    public void read() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String userCommand = scanner.nextLine().trim();
            if (userCommand.equals("save")) {
                ServerCommand saveCommand = new SaveCommand(collectionManager);
                saveCommand.execute();
            }
            if (userCommand.equals("exit")) {
                logger.info("Завершение работы сервера!");
                break;
            }

        }
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
}