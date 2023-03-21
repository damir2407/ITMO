package ask_works;


import messenger.Messenger;
import print_works.PrintInterface;
import request_structure.Request;
import request_structure.RequestInterface;

import java.util.*;

/**
 * for working with languages
 */
public class LanguageKeeper {
    private Messenger russianMessenger;
    private Messenger englishMessenger;
    private Map<String, Messenger> mapOfLanguages = new HashMap<>();
    private PrintInterface printMachine;
    private Messenger chosenMessenger;


    public LanguageKeeper(Messenger russianMessenger, Messenger englishMessenger, PrintInterface printMachine) {
        this.englishMessenger = englishMessenger;
        this.russianMessenger = russianMessenger;
        this.printMachine = printMachine;
    }

    /**
     * put languages to mapOfLanguages
     */
    public void putLanguage() {
        mapOfLanguages.put("rus", russianMessenger);
        mapOfLanguages.put("eng", englishMessenger);
    }

    /**
     * @return messenger to work with
     */
    public RequestInterface inputLanguage() {
        String language;
        try {
            Scanner scanner = new Scanner(System.in);
            printMachine.println("Выберите язык / Choose language" + "\nРусский (rus) / Английский (eng)");
            while (true) {

                language = scanner.nextLine().trim();
                if (!language.isEmpty()) {
                    if (!mapOfLanguages.containsKey(language)) {
                        printMachine.println("Данный язык не поддерживается / не существует\nThis language is not supported / does not exist");
                    } else {
                        setChosenMessenger(mapOfLanguages.get(language));
                        return new Request("language", language);
                    }
                }

            }
        } catch (NoSuchElementException e) {
        }
        return null;
    }

    public void setChosenMessenger(Messenger chosenMessenger) {
        this.chosenMessenger = chosenMessenger;
    }


    public Messenger getChosenMessenger() {
        return chosenMessenger;
    }
}

