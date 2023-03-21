package file_works;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import data.SpaceMarine;
import messenger.Messenger;
import messenger.PrintKeeper;

import java.io.FileNotFoundException;
import java.util.*;

public class Transform implements Transformer {
    private FileKeeper fileManager;
    private Gson gson;
    private FileFieldsChecker fileFieldsChecker;
    private PrintKeeper printMachine;
    private Messenger messenger;

    public Transform(FileKeeper fileManager, Gson gson, FileFieldsChecker fileFieldsChecker, PrintKeeper printMachine,
                     Messenger messenger) {
        this.fileManager = fileManager;
        this.fileFieldsChecker = fileFieldsChecker;
        this.printMachine = printMachine;
        this.gson = gson;
        this.messenger = messenger;
    }

    @Override
    public NavigableMap<Integer, SpaceMarine> convertFromJson() {
        NavigableMap<Integer, SpaceMarine> marines = null;
        try {
            marines = gson.fromJson(fileManager.load(), new TypeToken<NavigableMap<Integer, SpaceMarine>>() {
            }.getType());
            if (marines == null) throw new NoSuchElementException();

            if (fileFieldsChecker.check(marines).isOK())
                printMachine.println(messenger.collectionSuccessfullyMessage(marines.size()));
            else {
                printMachine.println(fileFieldsChecker.check(marines).getErrorMessage());
                marines.clear();
                printMachine.println(messenger.collectionSuccessfullyMessage(marines.size()));
            }
        } catch (JsonSyntaxException | NumberFormatException exception) {
            printMachine.println(messenger.jsonSyntaxMessage());
            printMachine.println(messenger.collectionSuccessfullyMessage(0));
            return null;
        } catch (NoSuchElementException e) {
            printMachine.println(messenger.noSuchElementInFileMessage());
            printMachine.println(messenger.collectionSuccessfullyMessage(0));
            return null;
        } catch (FileNotFoundException | NullPointerException exception) {
            printMachine.println(messenger.fileNotFoundMessage());
            printMachine.println(messenger.collectionSuccessfullyMessage(0));
            return null;
        } catch (SecurityException e) {
            printMachine.println(messenger.securityErrorMessage());
            printMachine.println(messenger.collectionSuccessfullyMessage(0));
            return null;
        }
        return marines;
    }
}
