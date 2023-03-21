package messenger;


import java.util.*;

/**
 * for working with languages
 */
public class LanguageKeeper {
    private Messenger russianMessenger;
    private Messenger englishMessenger;
    private Map<String, Messenger> mapOfLanguages = new HashMap<>();
    private PrintKeeper printMachine;


    public LanguageKeeper(Messenger russianMessenger, Messenger englishMessenger, PrintKeeper printMachine) {
        this.englishMessenger = englishMessenger;
        this.russianMessenger = russianMessenger;
        this.printMachine = printMachine;
        putLanguage();
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
    public Messenger inputLanguage() {
        String language;
        try {
            Scanner scanner = new Scanner(System.in);
            printMachine.println("Выберите язык / Choose language" + "\nРусский (rus) / Английский (eng)");
            while (true) {

                language = scanner.nextLine().trim();
                if (!language.isEmpty()) {
                    if (!mapOfLanguages.containsKey(language)) {
                        printMachine.println("Данный язык не поддерживается / не существует\nThis language is not supported / does not exist");
                        return null;
                    } else return mapOfLanguages.get(language);
                }

            }
        } catch (NoSuchElementException e) {
        }
        return null;
    }


}

