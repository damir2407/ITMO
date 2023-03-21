package commands;

import messenger.EnglishMessenger;
import messenger.Messenger;
import messenger.RussianMessenger;

import java.util.HashMap;
import java.util.Map;

public class LanguageManager {
    private Map<String, Messenger> mapOfLanguages = new HashMap<>();

    public LanguageManager() {
        putLanguage();
    }

    public void putLanguage() {
        mapOfLanguages.put("rus", new RussianMessenger());
        mapOfLanguages.put("eng", new EnglishMessenger());
    }

    public Map<String, Messenger> getMapOfLanguages() {
        return mapOfLanguages;
    }
}
