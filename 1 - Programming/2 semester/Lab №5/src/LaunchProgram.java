import base.*;
import com.google.gson.Gson;
import file_works.*;
import messenger.*;
import validate.FieldsValidation;
import validate.Validator;

import java.util.Scanner;

public class LaunchProgram {

    public void startProgram() {
        PrintKeeper printMachine = new PrintMachine();
        Messenger messenger = (new LanguageKeeper(new RussianMessenger(), new EnglishMessenger(), printMachine).inputLanguage());
        if (messenger == null) System.exit(0);
        Scanner scanner = new Scanner(System.in);
        Validator fieldsValidation = new FieldsValidation(messenger);
        PollKeeper poll = new Poll(scanner, fieldsValidation, messenger, printMachine);
        FileFieldsChecker fileFieldsChecker = new FileFieldsChecker(fieldsValidation, messenger);
        FileKeeper fileManager = new FileManager(System.getenv("My_Path"), messenger, printMachine);
        Transformer transform = new Transform(fileManager, new Gson(), fileFieldsChecker, printMachine, messenger);
        CollectionKeeper collectionManager = new CollectionManager(fileManager, transform);
        collectionManager.convertToCollection();
        SetKeeper marineSetter = new MarineSetter(poll);
        Repeater consoleRepeater = new ConsoleRepeater(collectionManager, marineSetter);
        CommandKeeper commandManager = new CommandManager(consoleRepeater);
        commandManager.createCommands(collectionManager, messenger, printMachine);
        commandManager.putCommands();
        Executor executor = new CommandExecutor(commandManager, poll, messenger, printMachine);
        Reader commandReader = new CommandReader(scanner);
        commandReader.interactiveMode(executor);
    }


}
