package file_works;

import com.google.gson.Gson;
import data.*;
import messenger.Messenger;
import messenger.PrintKeeper;

import java.io.*;
import java.util.*;

/**
 * Operates the file for saving/loading collection.
 */
public class FileManager implements FileKeeper {

    private Gson gson = new Gson();
    private String path;

    private Messenger messenger;
    private PrintKeeper printMachine;

    public FileManager(String path, Messenger messenger, PrintKeeper printMachine) {
        this.path = path;
        this.messenger = messenger;
        this.printMachine = printMachine;

    }


    @Override
    public String load() throws FileNotFoundException, SecurityException {
        String inputLine;
        StringBuilder result = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNext()) {
                inputLine = scanner.nextLine();
                result.append(inputLine);
            }
        }
        return result.toString();

    }

    /**
     * Writes collection to a file.
     *
     * @param collection Collection to write.
     */
    @Override
    public void writeCollection(NavigableMap<Integer, SpaceMarine> collection) {
        try (FileOutputStream collectionFileWriter = new FileOutputStream(new File(path))) {
            collectionFileWriter.write(gson.toJson(collection).getBytes());
            printMachine.println(messenger.successfullySave());
        } catch (FileNotFoundException | NullPointerException e) {
            printMachine.println(messenger.canNotSaveFile());
        } catch (IOException e) {
            printMachine.println(messenger.inputOutputMessage());
        }
    }

    @Override
    public String getPath() {
        return path;
    }


}

