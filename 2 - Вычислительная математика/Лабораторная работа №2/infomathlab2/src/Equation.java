import java.util.HashMap;
import java.util.Map;

public class Equation {
    private Map<String, String> mapOfEquations;


    {
        mapOfEquations = new HashMap<>();
        mapOfEquations.put("1", "x^3-1.89x^2-2x+1.76");
        mapOfEquations.put("2", "x^3-0.12x^2-1.475x+0.192");
        mapOfEquations.put("3", "-0.5-x^2+cos(x)");
    }

    public void setMapOfEquations(Map<String, String> mapOfEquations) {
        this.mapOfEquations = mapOfEquations;
    }


    public Map<String, String> getMapOfEquations() {
        return mapOfEquations;
    }

}
