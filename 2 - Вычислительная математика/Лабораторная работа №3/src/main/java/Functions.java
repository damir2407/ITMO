import java.util.Map;

public class Functions {
    private final Map<String, String> functionsMap;


    public Functions(Map<String, String> functionsMap) {
        this.functionsMap = functionsMap;
    }

    public Map<String, String> getFunctionsMap() {
        return functionsMap;
    }

    @Override
    public String toString() {
        return "Функции:\n" +
                "1: " + functionsMap.get("1")+"\n"+
                "2: " + functionsMap.get("2")+"\n"+
                "3: " + functionsMap.get("3");
    }
}
